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
import com.example.add_update_fetch_delete.databinding.FragmentFetchBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Fetch_Fragment : Fragment() {
    private lateinit var binding: FragmentFetchBinding
    lateinit var database: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFetchBinding.inflate(inflater,container,false)
        getActivity()?.setTitle("Inventory");
        context?.apply {
            database = DatabaseHelper.getDatabase(this)
        }

        val productList = binding.recyclerViewProductList

        fun updateData(pid:Long,pName:String,pPrice:Long,pQuentity:Long) {
          val direction = Fetch_FragmentDirections.actionFetchFragmentToUpdateFragment(pid,pName,pPrice,pQuentity)
           findNavController().navigate(direction)
        }

        fun deleteData(pid:Long) {
            GlobalScope.launch {
                database.productDao().delProduct(pid)
            }
            Toast.makeText(context, "Data Deleted successfully", Toast.LENGTH_LONG).show()
        }

        database.productDao().getProduct().observe(viewLifecycleOwner, Observer {

            fun itemClicked(pid:Long,pName:String,pPrice:Long,pQuentity:Long ){


                val dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setMessage("What Do you want to Do Update or Delete?")
                    .setCancelable(true)
                    .setPositiveButton("Update", DialogInterface.OnClickListener {
                            dialog, id -> updateData(pid,pName,pPrice,pQuentity)
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


        binding.floatingActionButton2.setOnClickListener{
            findNavController().navigate(R.id.action_fetch_Fragment_to_insert_Fragment)
        }

        return binding.root
    }

}