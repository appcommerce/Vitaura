package com.example.vitaura.ui.doctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemDoctorBinding
import com.example.vitaura.extensions.Constants
import com.example.vitaura.extensions.HTMLNormalizer
import com.example.vitaura.pojo.NodeDoctor
import com.squareup.picasso.Picasso

class DoctorsAdapter: RecyclerView.Adapter<DoctorsAdapter.DoctorsViewHolder>() {
    private var doctors = listOf<NodeDoctor>()
    private var photos = listOf<String>()
    private var listener: OnDoctorClickListener? = null

    fun setDoctors(list: List<NodeDoctor>, photos: List<String>){
        this.doctors = list
        this.photos = photos
        notifyDataSetChanged()
    }
    fun setDoctorListener(listener: OnDoctorClickListener){
        this.listener = listener
    }

    inner class DoctorsViewHolder(itemViewBind: ItemDoctorBinding): RecyclerView.ViewHolder(itemViewBind.root){
        val layout = itemViewBind
        fun bind(doctor: NodeDoctor, photo: String) = with(itemView){
            Picasso.get()
                    .load(photo)
                    .into(layout.portraitIv)
            layout.nameTv.text = doctor.title
            layout.specTv.text = doctor.post
            layout.description.text = HTMLNormalizer.normaliseWithoutMoreSpace(doctor.shortDescription.orEmpty())
            layout.moreBtn.setOnClickListener {
                listener?.getDoctorById(doctor.id)
            }
            layout.logInBtn.setOnClickListener {
                listener?.getCallback()
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorsViewHolder = DoctorsViewHolder(
        ItemDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: DoctorsViewHolder, position: Int) {
        holder.bind(doctors[position], photos[position])
    }
    override fun getItemCount(): Int = doctors.size
}