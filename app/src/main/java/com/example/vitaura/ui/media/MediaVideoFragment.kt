package com.example.vitaura.ui.media

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentMediaVideoBinding
import com.example.vitaura.extensions.Router
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.VideoAlbums
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.MediaViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MediaVideoFragment: BaseFragment(R.layout.fragment_media_video), OnVideoClickListener {
    private val layout by viewBinding(FragmentMediaVideoBinding::bind)
    private val mediaViewModel by sharedViewModel<MediaViewModel>()
    private val observerVideo = Observer<Results<List<VideoAlbums>>>{ handleAlbums(it) }
    private var albumAdapter: VideoAlbumAdapter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAlbumsList()
        mediaViewModel.getVideoAlbums().observe(viewLifecycleOwner, observerVideo)
    }

    private fun initAlbumsList() {
        albumAdapter = VideoAlbumAdapter()
        albumAdapter?.setVideoListener(this)
        layout.rvVideoAlbums.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = albumAdapter
        }
    }

    private fun handleAlbums(result: Results<List<VideoAlbums>>) {
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let {
                    albumAdapter?.setAlbums(it)
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

    override fun openVideo(id: String) {
        mediaViewModel.videoId = id
        Router.routeFragment(requireActivity(), VideoFragment(), R.id.main_container)
    }
}