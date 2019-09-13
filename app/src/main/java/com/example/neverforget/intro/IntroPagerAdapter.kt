package com.example.neverforget.intro

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.neverforget.R
import kotlinx.android.synthetic.main.intro_item_view.view.*

class IntroPagerAdapter(private val items:ArrayList<IntroItemsModel>,private val context: Context) :
    androidx.viewpager.widget.PagerAdapter(){

    val TAG = "INTRO PAGER ADAPTER"

    //custom interface for item position checking from intro activity
    interface ItemChangeListener{
        abstract fun changeItem(isCurentItemLast:Boolean,itemPosition: Int)
    }

    lateinit var itemChangeListener:ItemChangeListener


    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return  p0 == p1
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val inflater = LayoutInflater.from(context)

        val itemView = inflater.inflate(R.layout.intro_item_view,container,false)

        val curentItem = items.get(position)

        itemView.introItemTittleView.text = curentItem.tittle

        itemView.introItemShortDescrioptionView.text = curentItem.shortDescription

        itemView.introItemImgView.setImageResource(curentItem.img)

        itemView.introItemShortDescrioptionView.text = curentItem.description

        container.addView(itemView)

        Log.e(TAG,"inistianse called")

        return itemView

    }

    //getting a visible item position for check is item last
    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)

        if(position == items.size-1){
            itemChangeListener.changeItem(true,position)
        }else{
            itemChangeListener.changeItem(false,position)
        }

    }




}