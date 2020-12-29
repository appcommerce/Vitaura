package com.appcommerce.vitaura.ui.feedback

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.databinding.FragmentFeedbackBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Feedback
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.ui.mail.CallbackFragment
import com.appcommerce.vitaura.ui.mail.SendFeedFragment
import com.appcommerce.vitaura.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class FeedbackFragment: BaseFragment(R.layout.fragment_feedback) {
    private val mainViewModel: MainViewModel by viewModel()
    private val layout by viewBinding(FragmentFeedbackBinding::bind)
    private val feedsObserver = Observer<Results<List<Feedback>>> { handleFeedback(it) }
    private var feedbackAdapter: FeedbackAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFeedbackList()
        mainViewModel.getFeedback().observe(viewLifecycleOwner, feedsObserver)
        layout.incFlower.logInFlowerBtn.setOnClickListener {
            Router.routeFragment(requireActivity(), CallbackFragment(), R.id.main_container)
        }
        layout.sendFeed.setOnClickListener {
            Router.routeFragment(requireActivity(), SendFeedFragment(), R.id.main_container)
        }
    }

    /**
     * Получаем список отзывов от сервера
     */
    private fun handleFeedback(results: Results<List<Feedback>>){
        when(results){
            is Results.Success ->{
                hideLoading()
                results.data?.let {
                    fillFeedbackList(it)
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
     * Заполняем список отзывами
     */
    private fun fillFeedbackList(feeds: List<Feedback>){
        feedbackAdapter?.setFeedbackList(feeds)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    /**
     * Инициализируем Recycler для отзывов
     */
    private fun initFeedbackList(){
        feedbackAdapter = FeedbackAdapter()
        layout.rvFeeds.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = feedbackAdapter
        }
    }
}