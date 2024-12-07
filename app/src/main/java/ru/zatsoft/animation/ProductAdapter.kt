package ru.zatsoft.animation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter( private val products
:MutableList<Product>):
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        private var onProductClickListener: OnProductClickListener? =null

        interface OnProductClickListener{
            fun onProductClick(product: Product, position: Int)
        }


    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.item_image)
        val title: TextView = itemView.findViewById(R.id.item_title)
        val price: TextView = itemView.findViewById(R.id.item_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ProductViewHolder(itemView)
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.image.setImageResource(product.image)
        holder.title.text = product.name
        holder.price.text = product.price.toString()
        holder.itemView.setOnClickListener {
            if(onProductClickListener != null){
                onProductClickListener!!.onProductClick(product,position)
            }
        }
    }

    fun setOnProductClickListener(onProductClickListener: OnProductClickListener){
        this.onProductClickListener = onProductClickListener
    }
}