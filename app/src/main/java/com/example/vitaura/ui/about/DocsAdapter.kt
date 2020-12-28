package com.example.vitaura.ui.about

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemDocsBinding
import com.example.vitaura.pojo.Doc

class DocsAdapter: RecyclerView.Adapter<DocsAdapter.DocsViewHolder>() {
    private var docs = listOf<Doc>()
    fun setDocs(list: List<Doc>){
        this.docs = list
        notifyDataSetChanged()
    }
    inner class DocsViewHolder(itemViewBind: ItemDocsBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(doc: Doc) = with(itemView){

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