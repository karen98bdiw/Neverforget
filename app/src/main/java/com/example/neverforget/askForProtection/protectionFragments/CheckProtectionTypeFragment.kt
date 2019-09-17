package com.example.neverforget.askForProtection.protectionFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.example.neverforget.R
import kotlinx.android.synthetic.main.check_protection_type_view.*

class CheckProtectionTypeFragment : Fragment(){

    interface OnUserProtectionTypeCheckListener{
        abstract fun onProtectionCheck(isUserCheckPinProtection:Boolean);
    }

    lateinit public var onUserProtectionCheckListener:OnUserProtectionTypeCheckListener

    val TAG = "CHECK PROTECTION "

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.check_protection_type_view,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        withProtectionCheck.setOnClickListener {
            withouthProtectionCheck.isChecked = false
        }

        withouthProtectionCheck.setOnClickListener {
            withProtectionCheck.isChecked = false
        }

           protectionCheckBtn.setOnClickListener {
               if(withProtectionCheck.isChecked){
                   Log.e(TAG,"with pin")
                   onUserProtectionCheckListener.onProtectionCheck(true)

               }else{
                   Log.e(TAG,"without pin")
                   onUserProtectionCheckListener.onProtectionCheck(false)


               }
           }


        }

    }

