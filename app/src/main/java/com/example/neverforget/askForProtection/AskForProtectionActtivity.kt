package com.example.neverforget.askForProtection

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.neverforget.MainActivity
import com.example.neverforget.R
import com.example.neverforget.askForProtection.protectionFragments.CheckProtectionTypeFragment
import com.example.neverforget.askForProtection.protectionFragments.TakePinFragment

class AskForProtectionActtivity : AppCompatActivity() {


    lateinit var checkProtectionTypeFragment:CheckProtectionTypeFragment
    lateinit var takePinFragment: TakePinFragment
    lateinit var alertDialog:AlertDialog.Builder
    lateinit var inflater:LayoutInflater


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        if(supportActionBar != null){
            supportActionBar!!.hide()
        }

        setContentView(R.layout.activity_ask_for_protection_acttivity)

        //init
        alertDialog = AlertDialog.Builder(this@AskForProtectionActtivity)
        inflater = LayoutInflater.from(this@AskForProtectionActtivity)

        checkProtectionTypeFragment = CheckProtectionTypeFragment()
        takePinFragment = TakePinFragment()

        takePinFragment.onUserPinSignedListener = object :TakePinFragment.OnUserPinSignedListener{
            override fun userPinSign(isPinSigned: Boolean) {
                if(isPinSigned){
                    finish()
                    goToMain()
                }
            }

        }

        checkProtectionTypeFragment.onUserProtectionCheckListener = object:CheckProtectionTypeFragment.OnUserProtectionTypeCheckListener{
            override fun onProtectionCheck(isUserCheckPinProtection: Boolean) {
                if(isUserCheckPinProtection){
                    supportFragmentManager.beginTransaction().replace(R.id.protectonFragmentsContainer,takePinFragment).commit()
                }else{
                    showWarningDialog()

                }
            }
        }





        supportFragmentManager.beginTransaction().replace(R.id.protectonFragmentsContainer,checkProtectionTypeFragment).commit()
    }


    override fun onBackPressed() {
        supportFragmentManager.beginTransaction().replace(R.id.protectonFragmentsContainer,checkProtectionTypeFragment).commit()

    }

    fun goToMain(){
        finish()
        startActivity(Intent(this@AskForProtectionActtivity,MainActivity::class.java))
    }

    fun showWarningDialog(){

        val alertDialogView = inflater.inflate(R.layout.no_pin_warrning_dialog_view,null)

        alertDialog
        .setView(alertDialogView)
            .setPositiveButton("Agree",object:DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    saveProtectionStatusWithoutPin()
                    goToMain()
                }

            })
            .setNegativeButton("Disagree",object :DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }

            }).show()
    }

    fun saveProtectionStatusWithoutPin(){
        val sharedPreferences = applicationContext.getSharedPreferences("pin", Context.MODE_PRIVATE)

        val edit = sharedPreferences.edit()

        edit.putBoolean("isProtectionChecked",true)
        edit.putBoolean("isPinProtectionChecked",false)

        edit.commit()
    }
}
