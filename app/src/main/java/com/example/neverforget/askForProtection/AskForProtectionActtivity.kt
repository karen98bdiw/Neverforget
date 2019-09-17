package com.example.neverforget.askForProtection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.neverforget.R
import com.example.neverforget.askForProtection.protectionFragments.CheckProtectionTypeFragment
import com.example.neverforget.askForProtection.protectionFragments.TakePinFragment

class AskForProtectionActtivity : AppCompatActivity() {


    lateinit var checkProtectionTypeFragment:CheckProtectionTypeFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ask_for_protection_acttivity)

        checkProtectionTypeFragment = CheckProtectionTypeFragment()

        checkProtectionTypeFragment.onUserProtectionCheckListener = object:CheckProtectionTypeFragment.OnUserProtectionTypeCheckListener{
            override fun onProtectionCheck(isUserCheckPinProtection: Boolean) {
                if(isUserCheckPinProtection){
                    supportFragmentManager.beginTransaction().replace(R.id.protectonFragmentsContainer,TakePinFragment()).commit()
                }else{
                    showWarningDiolog()

                }
            }
        }

        supportFragmentManager.beginTransaction().replace(R.id.protectonFragmentsContainer,checkProtectionTypeFragment).commit()
    }

    fun showWarningDiolog(){
        Toast.makeText(this@AskForProtectionActtivity,"warning",Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        supportFragmentManager.beginTransaction().replace(R.id.protectonFragmentsContainer,checkProtectionTypeFragment).commit()

    }
}
