package com.appcommerce.vitaura.ui.doctors

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentCurrentDoctorBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.NodeDoctor
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.viewmodel.DoctorsViewModel
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel

class CurrentDoctorFragment: BaseFragment(R.layout.fragment_current_doctor), TabLayout.OnTabSelectedListener {
    private val layout by viewBinding(FragmentCurrentDoctorBinding::bind)
    private val docViewModel by sharedViewModel<DoctorsViewModel>()
    private val doctorObserver = Observer<Results<NodeDoctor>> { handleDoctor(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTabs()
        docViewModel.doctorId?.let {
            docViewModel.getDoctor(it).observe(viewLifecycleOwner, doctorObserver)
        } ?: kotlin.run {
            requireActivity().supportFragmentManager.popBackStack()
        }
        layout.callbackDocBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    private fun initTabs() {
        Router.routeTabFragment(this, DoctorInfoFragment(), R.id.doc_info_container)
        layout.docTab.addTab(layout.docTab.newTab().setText("Информация"))
        layout.docTab.addTab(layout.docTab.newTab().setText("Специализация"))
        layout.docTab.addTab(layout.docTab.newTab().setText("Образование"))
        layout.docTab.addOnTabSelectedListener(this)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity)
            .supportActionBar?.apply {
            title = "Специалисты"
        }
    }

    private fun handleDoctor(results: Results<NodeDoctor>){
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    Picasso.get()
                        .load(it.photoUrl?.get(0))
                        .into(layout.doctorAva)
                    layout.fioDoctor.text = it.title
                    layout.specialisation.text = it.post
                    val price = "${it.price ?: "---"}₽"
                    layout.priceDoc.text = price
                }
            }
            is Results.Loading ->{
                showLoading()
            }
            is Results.Error ->{
                hideLoading()
                handleError(results.throwable)
            }
        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0 -> Router.routeTabFragment(this, DoctorInfoFragment(), R.id.doc_info_container)
            1 -> Router.routeTabFragment(this, DoctorSpecFragment(), R.id.doc_info_container)
            2 -> Router.routeTabFragment(this, DoctorEduFragment(), R.id.doc_info_container)
        }
    }
    override fun onTabUnselected(tab: TabLayout.Tab?) {}
    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onDestroyView() {
        layout.docTab.removeOnTabSelectedListener(this)
        super.onDestroyView()
    }
}