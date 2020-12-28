package com.example.vitaura.ui.about

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vitaura.databinding.ItemAboutSlideBinding
import com.example.vitaura.pojo.AboutSlide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class AboutSliderAdapter: SliderViewAdapter<AboutSliderAdapter.SlideHolder>() {
    private var sliders = listOf<AboutSlide>()
    fun addSlides(slides: List<AboutSlide>){
        this.sliders = slides
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup?): SlideHolder = SlideHolder(
        ItemAboutSlideBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    )
    override fun onBindViewHolder(viewHolder: SlideHolder?, position: Int) {
        viewHolder?.bind(sliders[position])
    }
    override fun getCount(): Int = sliders.size
    inner class SlideHolder(itemViewBind: ItemAboutSlideBinding): ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(slider: AboutSlide) = Picasso.get()
            .load(slider.url)
            .into(layout.aboutImg)
    }
}