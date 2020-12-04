package com.example.vitaura.ui.main

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import com.example.vitaura.databinding.ItemSlideBinding
import com.example.vitaura.extensions.Constants
import com.example.vitaura.pojo.Slider
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class MainSliderAdapter: SliderViewAdapter<MainSliderAdapter.SlideHolder>() {
    private var sliders = listOf<Slider>()
    fun addSlides(slides: List<Slider>){
        this.sliders = slides
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup?): SlideHolder = SlideHolder(
            ItemSlideBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    )
    override fun onBindViewHolder(viewHolder: SlideHolder?, position: Int) {
        viewHolder?.bind(sliders[position])
    }
    override fun getCount(): Int = sliders.size
    inner class SlideHolder(itemViewBind: ItemSlideBinding): ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(slider: Slider) = with(itemView){
            val imageUrl = "${Constants.SERVER_URL}${slider.fieldPhoto3?.split("?")?.get(0)}"
            layout.titleSlider.text = slider.title
            layout.bodySlider.text = HtmlCompat.fromHtml(slider.body!!, HtmlCompat.FROM_HTML_MODE_LEGACY)
            Picasso.get()
                    .load(imageUrl)
                    .into(layout.imageSlide)
        }
    }
}