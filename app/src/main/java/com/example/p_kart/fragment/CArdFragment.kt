package com.example.p_kart.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.p_kart.R
import com.example.p_kart.activity.AddressActivity
import com.example.p_kart.activity.CategoryActivity
import com.example.p_kart.adapter.CartAdapter
import com.example.p_kart.databinding.FragmentCArdBinding
import com.example.p_kart.roomdb.AppDatabase
import com.example.p_kart.roomdb.ProductModel


class CArdFragment : Fragment() {
    private lateinit var binding: FragmentCArdBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentCArdBinding.inflate(layoutInflater)

        val preference=requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor=preference.edit()
        editor.putBoolean("isCart",false)
        editor.apply()

        val dao=AppDatabase.getInstance(requireContext()).productDao()
        dao.getAllProduct().observe(requireActivity()){
            binding.rvCartItems.adapter=CartAdapter(requireContext(),it)
            totalCost(it)
        }
        return binding.root
    }

    private fun totalCost(data:List<ProductModel>) {
        var total=0
        for(item in data!!){
            total+=item.productSP!!.toInt()
        }
        binding.textView12.text="Total items in the cart are ${data.size}"
        binding.textView13.text="Total Cost : $total"

        binding.checkout.setOnClickListener{
            val intent= Intent(context,AddressActivity::class.java)
            intent.putExtra("totalCost",total)
            startActivity(intent)
        }
    }

}