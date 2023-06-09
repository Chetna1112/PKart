package com.example.p_kart.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat.getCategory
import com.example.p_kart.R
import com.example.p_kart.adapter.CategoryAdapter
import com.example.p_kart.databinding.FragmentHomeBinding
import com.example.p_kart.model.AddProductModel
import com.example.p_kart.model.CategoryModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {

  private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(layoutInflater)
        getCategory()
        getProducts()
        return binding.root
    }

    private fun getProducts() {
        val list=ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get()
            .addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data=doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
            //    binding.categoryRecyclerView.adapter= CategoryAdapter(requireContext(),list)
            }
    }

    private fun getCategory() {
        val list=ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get()
            .addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data=doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecyclerView.adapter= CategoryAdapter(requireContext(),list)
            }
    }


}