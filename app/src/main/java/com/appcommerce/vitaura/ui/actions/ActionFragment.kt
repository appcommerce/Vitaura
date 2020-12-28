package com.appcommerce.vitaura.ui.actions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentActionBinding
import com.appcommerce.vitaura.extensions.HTMLNormalizer
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Action
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.viewmodel.ActionViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel

class ActionFragment: BaseFragment(R.layout.fragment_action), OnActionClickListener {
    private val layout by viewBinding(FragmentActionBinding::bind)
    private val actionViewModel by sharedViewModel<ActionViewModel>()
    private val observerAction = Observer<Results<Action>>{ handleAction(it) }
    private val observerActions = Observer<Results<List<Action>>>{ handleActions(it) }
    private var actionsAdapter: ActionsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionsList()
        if(actionViewModel.actionId.isNotEmpty()){
            actionViewModel.getAction(actionViewModel.actionId).observe(viewLifecycleOwner, observerAction)
            actionViewModel.getActions().observe(viewLifecycleOwner, observerActions)
        }
        layout.callbackAction.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    private fun handleAction(result: Results<Action>) {
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    showAction(it)
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

    private fun handleActions(result: Results<List<Action>>) {
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    if(actionViewModel.actionId.isNotEmpty()){
                        actionsAdapter?.setActionList(it.filter { it.id != actionViewModel.actionId })
                    }
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

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "Акции"
            }
    }

    private fun showAction(action: Action) {
        layout.imgTitle.text = action.title
        Picasso.get().load(action.imgUrlMax)
            .into(layout.actionImgMax)
        layout.bodyTitle.text = action.title
        layout.bodyDescription.text = HTMLNormalizer.normalise(action.description.orEmpty())
    }

    private fun initActionsList() {
        actionsAdapter = ActionsAdapter()
        actionsAdapter?.setActionClick(this)
        layout.rvOtherActions.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = actionsAdapter
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