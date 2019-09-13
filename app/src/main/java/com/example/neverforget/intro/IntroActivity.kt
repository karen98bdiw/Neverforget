package com.example.neverforget.intro

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.neverforget.R
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {

    var position = 0

    val TAG = "INTRO ACTIVITY"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val items = ArrayList<IntroItemsModel>()

        items.add(IntroItemsModel("Welcome to Neverforget","The helpful assistant for everyday life tasks",R.drawable.facebook_icon,"nenejfniuwnviuwnviunwviunwerrbfvoyabeorvybqoeryvgoaeybvroyaebvoyqerbviqeyrvboqeryvboqeryvboqeybvoqeyrbvoqeuryvboqerybvoqeryvboqyv"))
        items.add(IntroItemsModel("Use Tags and Save All Here","",R.drawable.instagram_icon,"nenejfniuwnviuwnviunwviunwerrbfvoyabeorvybqoeryvgoaeybvroyaebvoyqerbviqeyrvboqeryvboqeryvboqeybvoqeyrbvoqeuryvboqerybvoqeryvboqyv"))
        items.add(IntroItemsModel("Simple and Secure","",R.drawable.whatsapp_icon,"nenejfniuwnviuwnviunwviunwerrbfvoyabeorvybqoeryvgoaeybvroyaebvoyqerbviqeyrvboqeryvboqeryvboqeybvoqeyrbvoqeuryvboqerybvoqeryvboqyv"))
        items.add(IntroItemsModel("Get RFID Tags","",R.drawable.youtube_icon,"nenejfniuwnviuwnviunwviunwerrbfvoyabeorvybqoeryvgoaeybvroyaebvoyqerbviqeyrvboqeryvboqeryvboqeybvoqeyrbvoqeuryvboqerybvoqeryvboqyv"))


        val adapter = IntroPagerAdapter(items,this@IntroActivity)

        introPagerView.adapter = adapter

        //next btn turn view pager in next item position
        introNextBtn.setOnClickListener {

            introPagerView.currentItem = ++position

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
}
