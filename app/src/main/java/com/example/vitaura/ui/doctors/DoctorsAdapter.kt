package com.example.vitaura.ui.doctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemDoctorBinding
import com.example.vitaura.pojo.CurrentDoctor

class DoctorsAdapter: RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder>() {
    private var doctors = listOf<CurrentDoctor>()
    private var listener: OnDoctorClickListener? = null

    fun setDoctors(list: List<CurrentDoctor>){
        this.doctors = list
        notifyDataSetChanged()
    }
    fun setDoctorListener(listener: OnDoctorClickListener){
        this.listener = listener
    }

    inner class DoctorsViewHolder(itemViewBind: ItemDoctorBinding): RecyclerView.ViewHolder(itemViewBind.root){
        fun bind(doctor: CurrentDoctor) = with(itemView){

        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder = DoctorsViewHolder(
        ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        holder.bind(doctors[position])
    }
    override fun getItemCount(): Int = doctors.size
}