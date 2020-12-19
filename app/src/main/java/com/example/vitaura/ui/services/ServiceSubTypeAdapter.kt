package com.example.vitaura.ui.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemServiceSubTypeBinding
import com.example.vitaura.pojo.Service

class ServiceSubTypeAdapter: RecyclerView.Adapter<ServiceSubTypeAdapter.ServiceSubTypeViewHolder>() {
    private var parentSubTypes = listOf<Service>()
    private var childrenSubTypes = listOf<Service>()
    private var serviceClickListener: OnServiceSubTypeClickListener? = null
    inner class ServiceSubTypeViewHolder(itemViewBind: ItemServiceSubTypeBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(service: Service) = with(itemView){
            initInnerServiceList(service)
            layout.subName.text = service.title
            layout.expandBtn.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) layout.rvServiceList.visibility = View.VISIBLE
                else layout.rvServiceList.visibility = View.GONE
            }
        }

        /**
         * Внутренний список
         */
        private fun initInnerServiceList(service: Service){
            layout.rvServiceList.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                adapter = ServiceAdapter(
                    childrenSubTypes.filter { it.parentTargetId == service.tid },
                    serviceClickListener)
            }
        }
    }
    fun setMainSubTypes(list: List<Service>){
        this.parentSubTypes = list.filter { it.parentTargetId.isNullOrEmpty() }
        this.childrenSubTypes = list
        notifyDataSetChanged()
    }
    fun setServiceClickListener(listener: OnServiceSubTypeClickListener){
        this.serviceClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceSubTypeViewHolder = ServiceSubTypeViewHolder(
        ItemServiceSubTypeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: ServiceSubTypeViewHolder, position: Int) {
        holder.bind(parentSubTypes[position])
    }
    override fun getItemCount(): Int = parentSubTypes.size
}