package com.example.add_update_fetch_delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Update_Fragment : Fragment() {
    lateinit var database: DatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_update_, container, false)

        context?.apply {
            database = DatabaseHelper.getDatabase(this)
        }

       database.productDao().getUpdateProduct(1).observe(viewLifecycleOwner, Observer {

           
       })



        return view
    }

}