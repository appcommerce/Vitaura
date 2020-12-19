package com.example.vitaura.ui.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemServiceSubTypeBinding
import com.example.vitaura.pojo.Service

class ServiceSubTypeAdapter: RecyclerView.Adapter<ServiceSubTypeAdapter.ServiceSubTypeViewHolder>() {
    private var subTypes = listOf<Service>()

    inner class ServiceSubTypeViewHolder(itemViewBind: ItemServiceSubTypeBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(service: Service) = with(itemView){
            layout.nameService.text = service.title
            layout.goServiceBtn.setOnClickListener {
                println(service.type)
            }
        }
    }
    fun setSubTypes(list: List<Service>){
        this.subTypes = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceSubTypeViewHolder = ServiceSubTypeViewHolder(
        ItemServiceSubTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: ServiceSubTypeViewHolder, position: Int) {
        holder.bind(subTypes[position])
    }
    override fun getItemCount(): Int = subTypes.size
}