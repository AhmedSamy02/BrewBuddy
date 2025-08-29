package com.example.brewbuddy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.data.remote.api.CoffeeApiModel

class CoffeeAdapter(
    private var coffeeList: List<CoffeeApiModel>,
    private val onAddClick: (CoffeeApiModel) -> Unit // callback for add button
) : RecyclerView.Adapter<CoffeeAdapter.CoffeeViewHolder>() {

    private var filteredList: List<CoffeeApiModel> = coffeeList

    fun filter(query: String) {
        filteredList = if (query.isEmpty()) {
            coffeeList
        } else {
            coffeeList.filter {
                it.title?.contains(query, ignoreCase = true) ?: false
            }
        }
        notifyDataSetChanged()
    }

    fun updateData(newList: List<CoffeeApiModel>) {
        coffeeList = newList
        filteredList = newList
        notifyDataSetChanged()
    }

    class CoffeeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val coffeeImage: ImageView = itemView.findViewById(R.id.coffeeImage)
        val coffeeName: TextView = itemView.findViewById(R.id.coffeeName)
        val coffeePrice: TextView = itemView.findViewById(R.id.coffeePrice)
        val coffeeDescription: TextView = itemView.findViewById(R.id.coffeeDescription)
        val addButton: ImageButton = itemView.findViewById(R.id.addCoffeeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_coffee, parent, false)
        return CoffeeViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: CoffeeViewHolder, position: Int) {
        val coffee = filteredList[position]
        holder.coffeeName.text = coffee.title ?: "Unknown Coffee"
        holder.coffeePrice.text = "Price: ${coffee.price ?: "$0.00"}"
        holder.coffeeDescription.text = coffee.description ?: "No description available"

        Glide.with(holder.itemView.context)
            .load(coffee.image)
            .into(holder.coffeeImage)

        holder.addButton.setOnClickListener {
            onAddClick(coffee)
        }
    }
}