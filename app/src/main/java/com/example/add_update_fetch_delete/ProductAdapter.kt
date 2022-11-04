package com.example.add_update_fetch_delete

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ProductAdapter(
    var productList: List<Product>,
    val itemClicked: (pid: Long, pName: String, pPrice: Long, pQuentity: Long) -> Unit) : Adapter<ProductAdapter.myViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.item_list, parent, false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        holder.nProduct.text = productList.get(position).productName
        holder.productPrice.text = productList.get(position).productPrice.toString()
        holder.productQuantity.text = productList.get(position).productQuantity.toString()

        holder.listClick.setOnClickListener {

            var pName = productList.get(position).productName
            var pPrice = productList.get(position).productPrice.toString().toLong()
            var pQuentity = productList.get(position).productQuantity.toString().toLong()

            var pid = productList.get(position).productId.toString().toLong()
            itemClicked(pid, pName, pPrice, pQuentity)


        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class myViewHolder(itemView: View) : ViewHolder(itemView) {
        var nProduct = itemView.findViewById<TextView>(R.id.listProduct)
        var productPrice = itemView.findViewById<TextView>(R.id.listProPrice)
        var productQuantity = itemView.findViewById<TextView>(R.id.listQuantity)
        var listClick = itemView.findViewById<RelativeLayout>(R.id.itemListBtn)
    }
}
