package com.example.vitaura.extensions

import androidx.fragment.app.Fragment

object Router {
    fun routeTabFragment(from: Fragment, to: Fragment, container: Int){
        from.childFragmentManager.beginTransaction().replace(container, to)
                .commit()
    }
}