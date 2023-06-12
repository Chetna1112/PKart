package com.example.p_kart.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.p_kart.MainActivity
import com.example.p_kart.R
import com.example.p_kart.databinding.ActivityProductDetailBinding
import com.example.p_kart.roomdb.AppDatabase
import com.example.p_kart.roomdb.ProductDao
import com.example.p_kart.roomdb.ProductModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityProductDetailBinding.inflate(layoutInflater)
        getProductDetail(intent.getStringExtra("id"))
        setContentView(binding.root)
    }

    private fun getProductDetail(proID: String?) {
        Firebase.firestore.collection("products")
            .document(proID!!).get().addOnSuccessListener {
                val list=it.get("productImages") as ArrayList<String>
                val name=it.getString("productName")
               val productSP=it.getString("productSP")
                val productDescription =it.getString("productDescription")
                binding.textView7.text=name
                binding.textView8.text=productSP
                binding.textView9.text=productDescription
                val slideList=ArrayList<SlideModel>()
                for(data in list){ slideList.add(SlideModel(data,ScaleTypes.CENTER_CROP))
                }
                cartAction(proID,name , productSP, it.getString("productCoverImg"))
                binding.imageSlider.setImageList(slideList)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Something Went Wrong",Toast.LENGTH_SHORT).show()
            }
    }

    private fun cartAction(proID: String, name: String?, productSP: String?, coverImg: String?) {
        val productDao=AppDatabase.getInstance(this).productDao()
        if(productDao.isExist(proID)!=null){
        binding.textView10.text="Go To Cart"
        }
        else{
            binding.textView10.text="Add To Cart"
        }
        binding.textView10.setOnClickListener {
            if(productDao.isExist(proID)!=null){
               openCart()
            }
            else{
                addToCart(productDao,proID,name,productSP,coverImg)
            }
        }
    }

    private fun addToCart(
        productDao: ProductDao,
        proID: String,
        name: String?,
        productSP: String?,
        coverImg: String?) {
        val data=ProductModel(proID,name,coverImg,productSP)
        lifecycleScope.launch(Dispatchers.IO) {
            productDao.insertProduct(data)
            binding.textView10.text="Go To Cart"
        }
    }

    private fun openCart() {
        val preference=this.getSharedPreferences("info", MODE_PRIVATE)
        val editor=preference.edit()
        editor.putBoolean("isCart",true)
        editor.apply()

        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}