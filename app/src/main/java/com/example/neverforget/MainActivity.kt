package com.example.neverforget

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pref = applicationContext.getSharedPreferences("pin",Context.MODE_PRIVATE)

        val isPinSigned = pref.getBoolean("isPinProtectionChecked",false)

        if(isPinSigned){
            val pin = pref.getString("pin","pin")
            showPin.setText(pin)
        }else{
            showPin.setText("noPinProtection")
        }
    }
}
