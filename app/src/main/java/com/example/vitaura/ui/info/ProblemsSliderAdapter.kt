package com.example.vitaura.ui.info

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.vitaura.databinding.ItemProblemsBinding
import com.example.vitaura.extensions.Constants
import com.example.vitaura.pojo.PopularProblems
import com.smarteist.autoimageslider.SliderViewAdapter
import com.squareup.picasso.Picasso

class ProblemsSliderAdapter: SliderViewAdapter<ProblemsSliderAdapter.ProblemsHolder>() {
    private var problems = listOf<PopularProblems>()
    private var problemListener: OnProblemClickListener? = null
    override fun getCount(): Int = problems.size
    override fun onCreateViewHolder(parent: ViewGroup?): ProblemsHolder = ProblemsHolder(
            ItemProblemsBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
    )
    override fun onBindViewHolder(viewHolder: ProblemsHolder?, position: Int) {
        viewHolder?.bind(problems[position])
    }

    fun setProblems(list: List<PopularProblems>){
        this.problems = list
        notifyDataSetChanged()
    }

    fun setProblemClickListener(listener: OnProblemClickListener){
        problemListener = listener
    }

    inner class ProblemsHolder(itemViewBind: ItemProblemsBinding): ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(problem: PopularProblems) = with(itemView){
            val img = "${Constants.SERVER_URL}${problem.url}"
            layout.titleProblem.text = problem.title
            Picasso.get()
                .load(img)
                .into(layout.imgProblem)
            itemView.setOnClickListener {
                problem.services?.let { service->
                    problemListener?.getServicesByProblem(service)
                }
            }
        }
    }
}