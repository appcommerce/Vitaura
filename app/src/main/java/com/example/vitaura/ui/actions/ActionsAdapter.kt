package com.example.vitaura.ui.actions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemActionBinding
import com.example.vitaura.pojo.Action
import com.squareup.picasso.Picasso

class ActionsAdapter: RecyclerView.Adapter<ActionsAdapter.ActionsViewHolder>() {
    private var actions = listOf<Action>()
    private var listener: OnActionClickListener? = null
    fun setActionClick(listener: OnActionClickListener){
        this.listener = listener
    }
    fun setActionList(list: List<Action>){
        this.actions = list
        notifyDataSetChanged()
    }
    inner class ActionsViewHolder(itemViewBind: ItemActionBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(action: Action) = with(itemView){
            Picasso.get().load(action.imgUrlMax)
                .into(layout.actionImgMin)
            layout.imgTitle.text = action.title
            itemView.setOnClickListener {
                action.id?.let {
                    listener?.actionClick(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActionsViewHolder = ActionsViewHolder(
        ItemActionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ActionsViewHolder, position: Int) {
        holder.bind(actions[position])
    }

    override fun getItemCount(): Int = actions.size
}