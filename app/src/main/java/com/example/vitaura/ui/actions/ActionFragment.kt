package com.example.vitaura.ui.actions

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentActionBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Action
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.ActionViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ActionFragment: BaseFragment(R.layout.fragment_action) {
    private val layout by viewBinding(FragmentActionBinding::bind)
    private val actionViewModel by sharedViewModel<ActionViewModel>()
    private val observerAction = Observer<Results<Action>>{ handleAction(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(actionViewModel.actionId.isNotEmpty()){
            actionViewModel.getAction(actionViewModel.actionId).observe(viewLifecycleOwner, observerAction)
        }
    }

    private fun handleAction(result: Results<Action>) {
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {

                }
            }
            is Results.Loading ->{
                showLoading()
            }
            is Results.Error ->{
                hideLoading()
                handleError(result.throwable)
            }
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}