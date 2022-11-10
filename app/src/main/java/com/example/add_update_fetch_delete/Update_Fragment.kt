package com.example.add_update_fetch_delete

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.add_update_fetch_delete.databinding.FragmentUpdateBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Update_Fragment : Fragment() {
    private  lateinit var  binding: FragmentUpdateBinding
    lateinit var database: DatabaseHelper
    private val args : Update_FragmentArgs by navArgs()
    private var pId : Long = 0
    private var pName :String=  ""
    private var pPrice: Long = 0
    private var pQuentity: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        getActivity()?.setTitle("Edit Product");

        pId = args.pid
        pName = args.pName
        pPrice = args.pPrice
        pQuentity = args.pQuenitity

        context?.apply {
            database = DatabaseHelper.getDatabase(this)
        }
        binding.proName.setText(pName)
        binding.proPrice.setText(pPrice.toString())
        binding.proStock.setText(pQuentity.toString())

        binding.btnUpdate.setOnClickListener {
            var pNAME = binding.proName.text.toString()
            var pPRICE = binding.proPrice.text.toString()
            var pQUANTITY = binding.proStock.text.toString()
            if(pNAME.isEmpty() || pPRICE.isEmpty() || pQUANTITY.isEmpty()) {
                Toast.makeText(context, "All Fields are Required", Toast.LENGTH_LONG).show()
            }
            else{

                var pPRICE1 = pPRICE.toLong()
                var pQUANTITY1= pQUANTITY.toLong()
                GlobalScope.launch {
                    database.productDao().getUpdateProduct(pId, pNAME, pPRICE1, pQUANTITY1)
                }
                Toast.makeText(context, "Data Updated successfully", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_update_Fragment_to_fetch_Fragment)

        }
        }
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_update_Fragment_to_fetch_Fragment)
        }
        return binding.root
    }

}


