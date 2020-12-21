package com.example.vitaura.ui.services

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemServiceSubTypeBinding
import com.example.vitaura.pojo.ServiceSubMenu

class ServiceSubTypeAdapter: RecyclerView.Adapter<ServiceSubTypeAdapter.ServiceSubTypeViewHolder>() {
    private var parentSubTypes = listOf<ServiceSubMenu>()
    private var childrenSubTypes = listOf<ServiceSubMenu>()
    private var serviceClickListener: OnServiceSubTypeClickListener? = null
    inner class ServiceSubTypeViewHolder(itemViewBind: ItemServiceSubTypeBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(service: ServiceSubMenu) = with(itemView){
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
        private fun initInnerServiceList(service: ServiceSubMenu){
            layout.rvServiceList.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                setHasFixedSize(true)
                adapter = ServiceAdapter(
                    childrenSubTypes.filter { it.parentTargetId == service.tid },
                    serviceClickListener)
            }
        }
    }
    fun setMainSubTypes(list: List<ServiceSubMenu>){
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