package com.example.vitaura.ui.actions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentActionsBinding
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Action
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.mail.CallbackFragment
import com.example.vitaura.viewmodel.ActionViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ActionsFragment: BaseFragment(R.layout.fragment_actions), OnActionClickListener {
    private val layout by viewBinding(FragmentActionsBinding::bind)
    private val actionViewModel by sharedViewModel<ActionViewModel>()
    private val observerActions = Observer<Results<List<Action>>>{ handleActions(it) }

    private var actionsAdapter: ActionsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionsList()
        actionViewModel.getActions().observe(viewLifecycleOwner, observerActions)
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Акции"
            }
    }

    private fun initActionsList() {
        actionsAdapter = ActionsAdapter()
        actionsAdapter?.setActionClick(this)
        layout.rvActions.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = actionsAdapter
        }
    }

    private fun handleActions(result: Results<List<Action>>) {
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    actionsAdapter?.setActionList(it)
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

    override fun actionClick(id: String) {
        actionViewModel.actionId = id
        Router.routeFragment(requireActivity(), ActionFragment(), R.id.main_container)
    }
}