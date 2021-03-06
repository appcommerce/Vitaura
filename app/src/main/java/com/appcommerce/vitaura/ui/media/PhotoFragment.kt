package com.appcommerce.vitaura.ui.media

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentPhotosBinding
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.pojo.Gallery
import com.appcommerce.vitaura.pojo.Results
import com.appcommerce.vitaura.ui.base.BaseFragment
import com.appcommerce.vitaura.viewmodel.MediaViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class PhotoFragment: BaseFragment(R.layout.fragment_photos) {
    private val layout by viewBinding(FragmentPhotosBinding::bind)
    private val mediaViewModel by sharedViewModel<MediaViewModel>()
    private val observerPhotos = Observer<Results<List<Gallery>>>{ handlePhotos(it) }
    private var photoAdapter: PhotoAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPhotosList()
        when(mediaViewModel.albumId){
            "a4e3691a-17ba-4b6b-8653-439382d67f4a" -> mediaViewModel.getClinicPhotos().observe(viewLifecycleOwner, observerPhotos)
            "do-posle" -> mediaViewModel.getChangeGallery().observe(viewLifecycleOwner, observerPhotos)
            "gracia" -> mediaViewModel.getChampionshipPhotos().observe(viewLifecycleOwner, observerPhotos)
        }
    }

    private fun initPhotosList() {
        photoAdapter = PhotoAdapter()
        layout.rvClinicPh.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = photoAdapter
        }
    }

    private fun handlePhotos(result: Results<List<Gallery>>){
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    photoAdapter?.setGallery(it)
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