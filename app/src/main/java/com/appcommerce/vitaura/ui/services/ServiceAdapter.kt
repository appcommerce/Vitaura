package com.appcommerce.vitaura.ui.services

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.databinding.ItemServicesBinding
import com.appcommerce.vitaura.pojo.ServiceSubMenu

class ServiceAdapter(private val services: List<ServiceSubMenu>, private val listener: OnServiceSubTypeClickListener?): RecyclerView.Adapter<ServiceAdapter.ServiceViewHolder>() {
    inner class ServiceViewHolder(itemViewBind: ItemServicesBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(service: ServiceSubMenu) = with(itemView){
            layout.serviceName.text = service.title
            val servicePage = if(service.link?.startsWith("/services")!!) service.link else "/services${service.link}"
            layout.serviceName.setOnClickListener {
                listener?.getServiceById(service.tid, servicePage)
            }
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