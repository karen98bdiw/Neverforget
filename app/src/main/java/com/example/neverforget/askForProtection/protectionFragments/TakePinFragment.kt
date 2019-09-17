package com.example.neverforget.askForProtection.protectionFragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.neverforget.MainActivity
import com.example.neverforget.R
import kotlinx.android.synthetic.main.take_pin_fragment_view.*

class TakePinFragment : Fragment(){

    interface OnUserPinSignedListener{
        abstract fun userPinSign(isPinSigned:Boolean)
    }

    lateinit var onUserPinSignedListener:OnUserPinSignedListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.take_pin_fragment_view,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signInPinBtn.setOnClickListener {
            var pin1 = inputPinFirstView.text.toString()
            var pin2 = inputPinSecondView.text.toString()

            if(validatePinCodes(pin1,pin2)){
                savePIN(pin1)

                onUserPinSignedListener.userPinSign(true)
            }else{
                Toast.makeText(context,"wrong pin condes",Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun savePIN(pin1: String) {
        val sharedPreferences = activity!!.applicationContext.getSharedPreferences("pin", Context.MODE_PRIVATE)

        val edit = sharedPreferences.edit()

        edit.putString("pin",pin1)
        edit.putBoolean("isProtectionChecked",true)
        edit.putBoolean("isPinProtectionChecked",true)

        edit.commit()
    }

    private fun validatePinCodes(pin1: String, pin2: String):Boolean {
            if(pin1.equals(pin2)){
                Log.e("pinValidation","true")
                return true
            }
        Log.e("pinValidation","false")
        return false
    }



}
