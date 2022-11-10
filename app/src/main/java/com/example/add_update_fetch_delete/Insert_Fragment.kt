package com.example.add_update_fetch_delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.room.Database
import com.example.add_update_fetch_delete.databinding.FragmentInsertBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Insert_Fragment : Fragment() {
    lateinit var database: DatabaseHelper
    private lateinit var binding: FragmentInsertBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInsertBinding.inflate(inflater,container,false)

        getActivity()?.setTitle("Add Product");
        context?.apply {
            database = DatabaseHelper.getDatabase(this)
        }

        binding.btnInsert.setOnClickListener {
            var pNAME = binding.proName.text.toString()
            var pPRICE = binding.proPrice.text.toString()
            var pQUANTITY = binding.proStock.text.toString()

            if (pNAME.isEmpty() || pPRICE.isEmpty() || pQUANTITY.isEmpty()) {

                Toast.makeText(context, "All Fields are Required", Toast.LENGTH_LONG).show()

            } else {

                GlobalScope.launch {
                    database.productDao().insertProduct(Product(0, pNAME, pPRICE.toLong(), pQUANTITY.toLong()))
                }
                Toast.makeText(context, "Data Inserted successfully", Toast.LENGTH_LONG).show()
                binding.proName.setText("")
                binding.proPrice.setText("")
                binding.proStock.setText("")

                findNavController().navigate(R.id.action_insert_Fragment_to_fetch_Fragment)


            }

        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_insert_Fragment_to_fetch_Fragment)
        }


        return binding.root
    }
}