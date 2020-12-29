package com.appcommerce.vitaura.ui.feedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.databinding.ItemFeedbackBinding
import com.appcommerce.vitaura.pojo.Feedback

class FeedbackAdapter: RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>() {
    private var feeds = listOf<Feedback>()
    fun setFeedbackList(list: List<Feedback>){
        this.feeds = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder = FeedbackViewHolder(
        ItemFeedbackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.bind(feeds[position])
    }
    override fun getItemCount(): Int = feeds.size

    inner class FeedbackViewHolder(itemViewBind: ItemFeedbackBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(feed: Feedback) = with(itemView){
            layout.feedTitle.text = feed.title
            layout.feedDescription.text = HtmlCompat.fromHtml(feed.description.orEmpty(), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }
}