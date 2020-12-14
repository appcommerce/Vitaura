package com.example.vitaura.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object Router {
    fun routeTabFragment(from: Fragment, to: Fragment, container: Int){
        from.childFragmentManager.beginTransaction().replace(container, to)
                .addToBackStack(null)
                .commit()
    }
    fun routFragment(from: FragmentActivity, to: Fragment, container: Int){
                from
                .supportFragmentManager
                .beginTransaction()
                .replace(container, to)
                .addToBackStack(null)
                .commit()
    }
}