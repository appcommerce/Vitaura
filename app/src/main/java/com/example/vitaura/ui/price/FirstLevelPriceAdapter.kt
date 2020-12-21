package com.example.vitaura.ui.price

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitaura.databinding.ItemFirstPriceBinding
import com.example.vitaura.pojo.Prices
import com.example.vitaura.pojo.PricesCascade

class FirstLevelPriceAdapter: RecyclerView.Adapter<FirstLevelPriceAdapter.FirstLevelViewHolder>() {
    private var firstLevelList = listOf<Any>()

    inner class FirstLevelViewHolder(itemViewBind: ItemFirstPriceBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(firstLevelPrice: Any) = with(itemView){
            when(firstLevelPrice){
                is Prices -> initSimple(firstLevelPrice)
                is PricesCascade -> initCascade(firstLevelPrice)
            }
            layout.showSecondLevel.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) layout.rvSecondLevel.visibility = View.VISIBLE
                else layout.rvSecondLevel.visibility = View.GONE
            }
        }

        private fun initSimple(price: Prices){
            layout.nameFirst.text = price.name
            price.data?.let { secondLevel->
                layout.rvSecondLevel.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = SecondLevelPriceAdapter(secondLevel)
                }
            }
        }
        private fun initCascade(cascade: PricesCascade){
            layout.nameFirst.text = cascade.name
            cascade.data?.let { secondLevel->
                layout.rvSecondLevel.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = SecondLevelPriceAdapter(secondLevel)
                }
            }
        }
    }
    fun setFirstLevelPrice(items: List<Any>){
        this.firstLevelList = items
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirstLevelViewHolder = FirstLevelViewHolder(
            ItemFirstPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: FirstLevelViewHolder, position: Int) {
        holder.bind(firstLevelList[position])
    }
    override fun getItemCount(): Int = firstLevelList.size
}