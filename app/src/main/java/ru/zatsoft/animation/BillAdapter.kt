package ru.zatsoft.animation

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class BillAdapter(private val products
:MutableList<Product>):
    RecyclerView.Adapter<BillAdapter.BillViewHolder>() {

    class BillViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val title: TextView = itemView.findViewById(R.id.item_title)
        val price: TextView = itemView.findViewById(R.id.item_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.bill_item, parent, false)

        return BillViewHolder(itemView)
    }

    override fun getItemCount() = products.size

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.name
        holder.price.text = product.price.toString().plus(" руб.")
    }
}