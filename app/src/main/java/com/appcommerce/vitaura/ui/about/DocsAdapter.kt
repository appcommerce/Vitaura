package com.appcommerce.vitaura.ui.about

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.databinding.ItemDocsBinding
import com.appcommerce.vitaura.pojo.Doc
import com.squareup.picasso.Picasso

class DocsAdapter: RecyclerView.Adapter<DocsAdapter.DocsViewHolder>() {
    private var docs = listOf<Doc>()
    fun setDocs(list: List<Doc>){
        this.docs = list
        notifyDataSetChanged()
    }
    inner class DocsViewHolder(itemViewBind: ItemDocsBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(doc: Doc) = with(itemView){
            Picasso.get()
                .load(doc.url)
                .into(layout.docImg)
            layout.docTitle.text = doc.title
            layout.openDoc.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) layout.docImg.visibility = View.VISIBLE
                else layout.docImg.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DocsViewHolder = DocsViewHolder(
        ItemDocsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: DocsViewHolder, position: Int) {
        holder.bind(docs[position])
    }
    override fun getItemCount(): Int = docs.size
}