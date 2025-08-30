package org.geeksforgeeks.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewbuddy.R
import com.example.brewbuddy.data.local.database.entities.OrderHistory

class Adapter(private var list: List<OrderHistory>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var imageView: ImageView = view.findViewById(R.id.imgProduct)
        var orderQuantity: TextView = view.findViewById(R.id.orderQuantity_tv)
        var orderName: TextView = view.findViewById(R.id.orderName_tv)
        var date: TextView = view.findViewById(R.id.date_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.order_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val context = holder.itemView.context

        holder.orderQuantity.text = "${item.quantity}  "
        holder.orderName.text = item.title
        holder.date.text = item.date

        Glide.with(context)
            .load(item.image)
            .placeholder(R.drawable.sample)
            .error(R.drawable.sample)
            .into(holder.imageView)
    }

    override fun getItemCount() = list.size

    fun updateList(newList: List<OrderHistory>) {
        list = newList
        notifyDataSetChanged()
    }
}