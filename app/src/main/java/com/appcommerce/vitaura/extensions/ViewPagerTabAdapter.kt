package com.appcommerce.vitaura.extensions

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerTabAdapter(f: Fragment): FragmentStateAdapter(f) {
    private val fragmentList = ArrayList<Fragment>()
    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }
    override fun getItemCount() = fragmentList.size
    override fun createFragment(position: Int) = fragmentList[position]
}