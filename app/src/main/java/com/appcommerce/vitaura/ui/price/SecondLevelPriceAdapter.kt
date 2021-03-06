package com.appcommerce.vitaura.ui.price

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.ItemCascadeSecondPriceBinding
import com.appcommerce.vitaura.databinding.ItemSimpleSecondPriceBinding
import com.appcommerce.vitaura.pojo.Prices
import com.appcommerce.vitaura.pojo.PricesCascade

class SecondLevelPriceAdapter(private val secondLevelList: List<Any>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class SecondLevelViewHolder(itemViewBind: ItemSimpleSecondPriceBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(secondLevel: Any) = with(itemView){
            if (secondLevel is Prices.Price){
                val sumPrice = if (secondLevel.field_price_sum.isNullOrEmpty()) {
                    layout.simpleItem.setTextColor(ContextCompat.getColor(context, R.color.docPrice))
                    ""
                }
                else {
                    layout.simpleItem.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                    "${secondLevel.field_price_sum} р"
                }
                layout.simpleItem.text = secondLevel.field_price_item
                layout.simpleSum.text = sumPrice
            }
        }
    }

    inner class CascadeLevelViewHolder(itemViewBind: ItemCascadeSecondPriceBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(secondLevel: Any) = with(itemView){
            if (secondLevel is PricesCascade.Data){
                initCascadeLevel(secondLevel)
            }
            layout.showThreeLevel.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) layout.rvThreeLevel.visibility = View.VISIBLE
                else layout.rvThreeLevel.visibility = View.GONE
            }
        }
        private fun initCascadeLevel(secondLevel: PricesCascade.Data) {
            layout.nameCascade.text = secondLevel.name
            secondLevel.data?.let {
                layout.rvThreeLevel.apply {
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = ThreeLevelPriceAdapter(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       return when(viewType){
            R.layout.item_simple_second_price-> SecondLevelViewHolder(ItemSimpleSecondPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            R.layout.item_cascade_second_price -> CascadeLevelViewHolder(ItemCascadeSecondPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> SecondLevelViewHolder(ItemSimpleSecondPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
    override fun getItemViewType(position: Int): Int {
        return when(secondLevelList[position]){
            is Prices.Price -> {
                R.layout.item_simple_second_price
            }
            is PricesCascade.Data -> {
                R.layout.item_cascade_second_price
            }
            else -> R.layout.item_simple_second_price
        }
    }
    override fun getItemCount(): Int = secondLevelList.size
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            R.layout.item_simple_second_price -> {
                when(secondLevelList[position]){
                    is Prices.Price -> (holder as SecondLevelViewHolder).bind(secondLevelList[position])
                }
            }
            R.layout.item_cascade_second_price -> {
                when(secondLevelList[position]){
                    is PricesCascade.Data -> (holder as CascadeLevelViewHolder).bind(secondLevelList[position])
                }
            }
        }
    }
}