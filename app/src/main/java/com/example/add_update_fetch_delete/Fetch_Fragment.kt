package com.example.add_update_fetch_delete

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Fetch_Fragment : Fragment() {
    lateinit var database: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_fetch_, container, false)
        context?.apply {
            database = DatabaseHelper.getDatabase(this)
        }
        var productList = view.findViewById<RecyclerView>(R.id.recyclerView_Product_List)

        fun updateData(pid:Long) {

           findNavController().navigate(R.id.action_fetch_Fragment_to_update_Fragment)
        }

        fun deleteData(pid:Long) {
            GlobalScope.launch {
                database.productDao().delProduct(pid)
            }
            Toast.makeText(context, "Data Deleted successfully", Toast.LENGTH_LONG).show()
        }
        database.productDao().getProduct().observe(viewLifecycleOwner, Observer {

            fun itemClicked(pid:Long) {


                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("What Do you want to Do Update or Delete?")
                    .setCancelable(true)
                    .setPositiveButton("Update", DialogInterface.OnClickListener {
                            dialog, id -> updateData(pid)
                    })

                    .setNegativeButton("Delete", DialogInterface.OnClickListener {
                            dialog, id -> deleteData(pid)
                    })


                val alert = dialogBuilder.create()
                alert.setTitle("Hello Admin")
                alert.show()

            }


            productList.adapter= ProductAdapter(it,::itemClicked)
            productList.layoutManager = LinearLayoutManager(context)
        })


        view.findViewById<FloatingActionButton>(R.id.floatingActionButton2).setOnClickListener{
            findNavController().navigate(R.id.action_fetch_Fragment_to_insert_Fragment)
        }

        return view
    }

}