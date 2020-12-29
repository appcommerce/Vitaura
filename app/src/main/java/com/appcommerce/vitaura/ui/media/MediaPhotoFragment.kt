package com.appcommerce.vitaura.ui.media

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.FragmentMediaPhotoBinding
import com.appcommerce.vitaura.extensions.Router
import com.appcommerce.vitaura.extensions.viewBinding
import com.appcommerce.vitaura.viewmodel.MediaViewModel
import com.squareup.picasso.Picasso
import org.koin.android.viewmodel.ext.android.sharedViewModel

class MediaPhotoFragment: Fragment(R.layout.fragment_media_photo){
    private val layout by viewBinding(FragmentMediaPhotoBinding::bind)
    private val mediaViewModel by sharedViewModel<MediaViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Picasso.get()
                .load("https://vitaura-clinic.ru/sites/default/files/inline-images/_MG_0031.jpg")
                .resize(640, 426)
                .into(layout.galleryClinic)
        Picasso.get()
                .load("https://vitaura-clinic.ru/sites/default/files/inline-images/pp110220-01.jpg")

                .resize(640, 426)
                .into(layout.galleryResults)
        Picasso.get()
                .load("https://vitaura-clinic.ru/sites/default/files/inline-images/_MG_2215%20copy.jpg")

                .resize(640, 426)
                .into(layout.galleryGracia)
        layout.galleryClinic.setOnClickListener {
            mediaViewModel.albumId = "a4e3691a-17ba-4b6b-8653-439382d67f4a"
            Router.routeFragment(requireActivity(), PhotoFragment(), R.id.main_container)
        }
        layout.galleryResults.setOnClickListener {
            mediaViewModel.albumId = "do-posle"
            Router.routeFragment(requireActivity(), PhotoFragment(), R.id.main_container)
        }
        layout.galleryGracia.setOnClickListener {
            mediaViewModel.albumId = "gracia"
            Router.routeFragment(requireActivity(), PhotoFragment(), R.id.main_container)
        }
    }
}