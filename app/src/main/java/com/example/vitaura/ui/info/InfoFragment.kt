package com.example.vitaura.ui.info

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentInfoBinding
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Page
import com.example.vitaura.pojo.PopularProblems
import com.example.vitaura.pojo.Results
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.ui.mail.CallbackFragment
import com.example.vitaura.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class InfoFragment: BaseFragment(R.layout.fragment_info), OnProblemClickListener {
    private val mainViewModel: MainViewModel by viewModel()
    private val layout by viewBinding(FragmentInfoBinding::bind)
    private var problemsAdapter: ProblemsSliderAdapter? = null
    private val problemsObserver = Observer<Results<List<PopularProblems>>>{ handleProblems(it) }
    private val infoObserver = Observer<Results<List<Page>>>{ handleInformation(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProblemsSlider()
        mainViewModel.getPopularProblems().observe(viewLifecycleOwner, problemsObserver)
        mainViewModel.getPages().observe(viewLifecycleOwner, infoObserver)
        layout.incProblems.btnLeftSlide.setOnClickListener {
            layout.incProblems.rvPopular.slideToPreviousPosition()
        }
        layout.incProblems.btnRightSlide.setOnClickListener {
            layout.incProblems.rvPopular.slideToNextPosition()
        }
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
    }

    /**
     * Получаем список проблем с сервера
     */
    private fun handleProblems(results: Results<List<PopularProblems>>){
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    fillProblemsSlider(it)
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

    /**
     * Получаем информацию о клинике с сервера
     */
    private fun handleInformation(results: Results<List<Page>>){
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    layout.clinicDescription.text = HTMLNormalizer.normalise(it[0].text.orEmpty())
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

    /**
     * Наполняем слайдер
     */
    private fun fillProblemsSlider(problems: List<PopularProblems>){
        problemsAdapter?.setProblems(problems)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    private fun initProblemsSlider(){
        problemsAdapter = ProblemsSliderAdapter()
        problemsAdapter?.setProblemClickListener(this)
        problemsAdapter?.let {
            layout.incProblems.rvPopular.setSliderAdapter(it)
        }
    }

    override fun getServicesByProblem(services: String) {

    }
}