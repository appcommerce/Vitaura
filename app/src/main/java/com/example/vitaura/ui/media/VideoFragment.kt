package com.example.vitaura.ui.media

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.vitaura.R
import com.example.vitaura.databinding.FragmentVideoBinding
import com.example.vitaura.extensions.viewBinding
import com.example.vitaura.pojo.Results
import com.example.vitaura.pojo.VideoAlbums
import com.example.vitaura.ui.base.BaseFragment
import com.example.vitaura.viewmodel.MediaViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import org.koin.android.viewmodel.ext.android.sharedViewModel

class VideoFragment: BaseFragment(R.layout.fragment_video) {
    private val layout by viewBinding(FragmentVideoBinding::bind)
    private val mediaViewModel by sharedViewModel<MediaViewModel>()
    private val observerVideo = Observer<Results<VideoAlbums>>{ handleVideo(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycle.addObserver(layout.player)
        if (mediaViewModel.videoId.isNotEmpty()){
            mediaViewModel.getVideo(mediaViewModel.videoId).observe(viewLifecycleOwner, observerVideo)
        }
    }

    private fun handleVideo(result: Results<VideoAlbums>) {
        when(result){
            is Results.Success ->{
                hideLoading()
                result.data?.let { video->
                    println(video.youtube_id)
                    layout.player.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            video.youtube_id?.let {
                                youTubePlayer.cueVideo(it, 0F)
                            }
                        }
                    })
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

    override fun onDestroyView() {
        super.onDestroyView()
        lifecycle.removeObserver(layout.player)
    }

    override fun onDestroy() {
        super.onDestroy()
        layout.player.release()
    }

    override fun showLoading() {

    }
    override fun hideLoading() {

    }
}