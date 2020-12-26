package com.example.vitaura.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemPhotosBinding
import com.example.vitaura.pojo.Gallery
import com.squareup.picasso.Picasso

class PhotoAdapter: RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    private var gallery = listOf<Gallery>()
    inner class PhotoViewHolder(itemViewBind: ItemPhotosBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(photo: Gallery) = Picasso.get()
                .load(photo.images)
                .into(layout.picture)
    }
    fun setGallery(list: List<Gallery>){
        this.gallery = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder = PhotoViewHolder(
        ItemPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(gallery[position])
    }
    override fun getItemCount(): Int = gallery.size
}