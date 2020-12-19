package com.example.vitaura.ui.services

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemServiceTypeBinding
import com.example.vitaura.pojo.ServiceType

class ServiceTypeAdapter: RecyclerView.Adapter<ServiceTypeAdapter.ServiceTypeViewHolder>() {
    private var services = listOf<ServiceType>()
    private var serviceClickListener: OnServiceTypeClickListener? = null
    inner class ServiceTypeViewHolder(itemViewBind: ItemServiceTypeBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(serviceType: ServiceType) = with(itemView){
            layout.name.text = serviceType.type
            layout.servicePic.setImageBitmap(BitmapFactory.decodeResource(resources, serviceType.resId))
            layout.name.setOnClickListener {
                serviceClickListener?.getServiceByType(serviceType.aliasType)
            }
        }
    }
    fun setServiceTypes(list: List<ServiceType>){
        this.services = list
        notifyDataSetChanged()
    }
    fun setServiceTypeClickListener(listener: OnServiceTypeClickListener){
        this.serviceClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceTypeViewHolder = ServiceTypeViewHolder(
        ItemServiceTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: ServiceTypeViewHolder, position: Int) {
        holder.bind(services[position])
    }
    override fun getItemCount(): Int = services.size
}