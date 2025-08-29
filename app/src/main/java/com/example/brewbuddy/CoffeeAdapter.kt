package com.example.brewbuddy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.data.local.database.entities.CoffeeEntity

class CoffeeAdapter(
    private var coffeeList: List<CoffeeEntity>,
    private val onClick: (CoffeeEntity) -> Unit
) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    fun updateList(newList: List<CoffeeEntity>) {
        coffeeList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
        return CoffeeViewHolder(view)
    }

    override fun getItemCount(): Int = coffeeList.size

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val coffee = coffeeList[position]
        holder.coffeeName.text = coffee.title
        holder.coffeePrice.text = "Price: $${coffee.price}"
        Glide.with(holder.itemView.context)
            .load(coffee.imageUrl)
            .into(holder.coffeeImage)

        holder.itemView.setOnClickListener { onClick(coffee) }
    }

    class CoffeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coffeeImage: ImageView = itemView.findViewById(R.id.coffeeImage)
        val coffeeName: TextView = itemView.findViewById(R.id.coffeeName)
        val coffeePrice: TextView = itemView.findViewById(R.id.coffeePrice)
    }
}
