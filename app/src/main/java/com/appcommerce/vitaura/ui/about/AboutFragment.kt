package com.appcommerce.vitaura.ui.about

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentAboutBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.AboutSlide
import com.appcommerce.vitaura.pojo.Action
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.actions.ActionFragment
import com.appcommerce.vitaura.ui.actions.ActionsAdapter
import com.appcommerce.vitaura.ui.actions.OnActionClickListener
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.viewmodel.AboutViewModel
import com.appcommerce.vitaura.viewmodel.ActionViewModel
import com.google.android.material.tabs.TabLayout
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AboutFragment: BaseFragment(R.layout.fragment_about), OnActionClickListener, TabLayout.OnTabSelectedListener {
    private val layout by viewBinding(FragmentAboutBinding::bind)
    private val actionViewModel by sharedViewModel<ActionViewModel>()
    private val aboutViewModel by viewModel<AboutViewModel>()
    private val observerActions = Observer<Results<List<Action>>>{ handleActions(it) }
    private val observerAboutImages = Observer<List<AboutSlide>>{ handleAboutImages(it) }
    private var aboutSliderAdapter: AboutSliderAdapter? = null
    private var actionsAdapter: ActionsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initActionsList()
        initAboutSlider()
        initTabs()
        actionViewModel.getActions().observe(viewLifecycleOwner, observerActions)
        aboutViewModel.getAboutImages().observe(viewLifecycleOwner, observerAboutImages)
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
                title = "О нас"
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

    private fun handleAboutImages(result: List<AboutSlide>) {
        aboutSliderAdapter?.addSlides(result)
    }

    private fun initTabs(){
        Router.routeTabFragment(this, AboutInfoFragment(), R.id.tab_info_container)
        layout.aboutTab.addTab(layout.aboutTab.newTab().setText("Информация"))
        layout.aboutTab.addTab(layout.aboutTab.newTab().setText("Лицензия"))
        layout.aboutTab.addOnTabSelectedListener(this)
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

    private fun initAboutSlider() {
        aboutSliderAdapter = AboutSliderAdapter()
        aboutSliderAdapter?.let {
            layout.aboutSlider.setSliderAdapter(it)
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onDestroyView() {
        layout.aboutTab.removeOnTabSelectedListener(this)
        super.onDestroyView()
    }

    override fun actionClick(id: String) {
        actionViewModel.actionId = id
        Router.routeFragment(requireActivity(), ActionFragment(), R.id.main_container)
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> Router.routeTabFragment(this, AboutInfoFragment(), R.id.tab_info_container)
            1 -> Router.routeTabFragment(this, AboutLicenseFragment(), R.id.tab_info_container)
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}