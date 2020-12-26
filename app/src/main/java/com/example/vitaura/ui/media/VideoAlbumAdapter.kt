package com.example.vitaura.ui.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemVideoAlbumBinding
import com.example.vitaura.pojo.VideoAlbums

class VideoAlbumAdapter: RecyclerView.Adapter<VideoAlbumAdapter.VideoAlbumViewHolder>() {
    private var albums = listOf<VideoAlbums>()
    private var listener: OnVideoClickListener? = null
    fun setVideoListener(callback: OnVideoClickListener){
        this.listener = callback
    }
    fun setAlbums(list: List<VideoAlbums>){
        this.albums = list
        notifyDataSetChanged()
    }
    inner class VideoAlbumViewHolder(itemViewBind: ItemVideoAlbumBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(videoAlbum: VideoAlbums) = with(itemView){
            layout.albumTitle.text = videoAlbum.title
            itemView.setOnClickListener {
                videoAlbum.id?.let {
                    listener?.openVideo(it)
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAlbumViewHolder = VideoAlbumViewHolder(
            ItemVideoAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: VideoAlbumViewHolder, position: Int) {
        holder.bind(albums[position])
    }
    override fun getItemCount(): Int = albums.size
}