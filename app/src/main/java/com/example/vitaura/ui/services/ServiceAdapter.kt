package com.example.vitaura.ui.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemServicesBinding
import com.example.vitaura.pojo.Service

class ServiceAdapter(private var services: List<Service>): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    inner class ServiceViewHolder(itemViewBind: ItemServicesBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(service: Service) = with(itemView){
            layout.serviceName.text = service.title
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder = ServiceViewHolder(
        ItemServicesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(services[position])
    }
    override fun getItemCount(): Int = services.size
}