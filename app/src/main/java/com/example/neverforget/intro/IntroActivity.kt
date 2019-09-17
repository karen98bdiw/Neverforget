package com.example.neverforget.intro

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import com.example.neverforget.MainActivity
import com.example.neverforget.R
import com.example.neverforget.askForProtection.AskForProtectionActtivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    var position = 0

    val TAG = "INTRO ACTIVITY"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(isAppOnenedYet()){

            if(pinSignedStatus()){
                finish()
                startActivity(Intent(this@IntroActivity,MainActivity::class.java))
                overridePendingTransition(0,0)
            }else{
                finish()
                startActivity(Intent(this@IntroActivity,AskForProtectionActtivity::class.java))
                overridePendingTransition(0,0)
            }


        }

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_intro)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        val items = ArrayList<IntroItemsModel>()

        //get intro resources
        val firstTittle = applicationContext.resources.getString(R.string.intro_first_tittle)
        val firstShortDesc = applicationContext.resources.getString(R.string.intro_first_short_desc)
        val firstDesc = applicationContext.resources.getString(R.string.intro_first_desc)

        val secondTittle = applicationContext.resources.getString(R.string.intro_second_tittle)
        val secondDesc = applicationContext.resources.getString(R.string.intro_second_desc)

        val thirdTittle = applicationContext.resources.getString(R.string.intro_third_tittle)
        val thirdDesc = applicationContext.resources.getString(R.string.intro_third_desc)

        val fourthTittle = applicationContext.resources.getString(R.string.intro_fourth_tittle)
        val fourthDesc = applicationContext.resources.getString(R.string.intro_fourth_desc)
        val fourthUnderDescriptionLink = this@IntroActivity.resources.getString(R.string.tags_starter_pack_link_text)

        //create intro segments
        items.add(IntroItemsModel(firstTittle,firstShortDesc,R.drawable.facebook_icon,firstDesc,""))
        items.add(IntroItemsModel(secondTittle,"",R.drawable.instagram_icon,secondDesc,""))
        items.add(IntroItemsModel(thirdTittle,"",R.drawable.whatsapp_icon,thirdDesc,""))
        items.add(IntroItemsModel(fourthTittle,"",R.drawable.youtube_icon,fourthDesc,fourthUnderDescriptionLink))


        val adapter = IntroPagerAdapter(items,this@IntroActivity)

        introPagerView.adapter = adapter

        //next btn turn view pager in next item position
        introNextBtn.setOnClickListener {

            introPagerView.currentItem = ++position

        }

        introGetStartedBtn.setOnClickListener {
            saveOpeningData()
            finish()
            startActivity(Intent(this@IntroActivity,AskForProtectionActtivity::class.java))

        }

        skipIntroText.setOnClickListener {
            saveOpeningData()
            finish()
            startActivity(Intent(this@IntroActivity,AskForProtectionActtivity::class.java))

        }

        //init intro tab layout
        introTabLayout.setupWithViewPager(introPagerView)
        introTabLayout.clearOnTabSelectedListeners()
        val x = introTabLayout.getChildAt(0) as LinearLayout

        //remove touch listeners from tablayout
        for(i in 0 until x.childCount){
            x.getChildAt(i).setOnTouchListener(object:View.OnTouchListener{
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    return true
                }

            })
        }

        //set item change listener and check shoul show last item
        adapter.itemChangeListener = object: IntroPagerAdapter.ItemChangeListener{
            override fun changeItem(isCurentItemLast: Boolean,itemPosition:Int) {
                Log.e(TAG,"$isCurentItemLast")
                if(isCurentItemLast){
                    showLastItem()
                }else{
                    returnLastItem()
                }

                position = itemPosition
            }
        }


    }

    //show get started btn and hide others
    fun showLastItem(){
        introNextBtn.visibility = View.GONE
        skipIntroText.visibility = View.GONE
        introGetStartedBtn.visibility = View.VISIBLE
    }

    //hide get started btn and show others
    fun returnLastItem(){
        introNextBtn.visibility = View.VISIBLE
        skipIntroText.visibility = View.VISIBLE
        introGetStartedBtn.visibility = View.GONE
    }

    fun saveOpeningData(){
        val pref = applicationContext.getSharedPreferences("opening", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isAppOpennedBefore",true)
        editor.commit()
    }

    fun isAppOnenedYet():Boolean{
        val pref = applicationContext.getSharedPreferences("opening", Context.MODE_PRIVATE)

        val isOpened = pref.getBoolean("isAppOpennedBefore",false)
        return  isOpened
    }

    fun pinSignedStatus():Boolean{
        val pref = applicationContext.getSharedPreferences("pin",Context.MODE_PRIVATE)

        val isPinSigned = pref.getBoolean("isProtectionChecked",false)

        return isPinSigned
    }
}
