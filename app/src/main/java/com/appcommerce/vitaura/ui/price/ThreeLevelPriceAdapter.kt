package com.appcommerce.vitaura.ui.price

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.appcommerce.vitaura.R
import com.appcommerce.vitaura.databinding.ItemThreePriceBinding
import com.appcommerce.vitaura.pojo.PricesCascade

class ThreeLevelPriceAdapter(private val threeLevelList: List<PricesCascade.Price>): RecyclerView.Adapter<ThreeLevelPriceAdapter.ThreeLevelViewHolder>() {

    inner class ThreeLevelViewHolder(itemViewBind: ItemThreePriceBinding): RecyclerView.ViewHolder(itemViewBind.root){
        private val layout = itemViewBind
        fun bind(price: PricesCascade.Price) = with(itemView){
            val sumPrice = if (price.field_price_sum.isNullOrEmpty()) {
                layout.threeItem.setTextColor(ContextCompat.getColor(context, R.color.docPrice))
                ""
            }
            else {
                layout.threeItem.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
                "${price.field_price_sum} р"
            }
            layout.threeItem.text = price.field_price_item
            layout.threeSum.text = sumPrice
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThreeLevelViewHolder = ThreeLevelViewHolder(
            ItemThreePriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: ThreeLevelViewHolder, position: Int) {
        holder.bind(threeLevelList[position])
    }
    override fun getItemCount(): Int = threeLevelList.size
}