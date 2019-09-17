package com.example.neverforget.askForProtection.protectionFragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neverforget.MainActivity
import com.example.neverforget.R
import kotlinx.android.synthetic.main.take_pin_fragment_view.*

class TakePinFragment : Fragment(){

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
                startActivity(Intent(context,MainActivity::class.java))
            }
        }
    }

    private fun savePIN(pin1: String) {

    }

    private fun validatePinCodes(pin1: String, pin2: String):Boolean {
            return true
    }



}
