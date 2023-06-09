package com.example.p_kart.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.p_kart.R
import com.example.p_kart.databinding.LayoutCategoryItemBinding
import com.example.p_kart.model.CategoryModel

class CategoryAdapter(var context: Context, val list:ArrayList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){
    inner class CategoryViewHolder(view: View):RecyclerView.ViewHolder(view){
        var binding=LayoutCategoryItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       return CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_category_item,parent,false))
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
      holder.binding.textView2.text=list[position].cate
        Glide.with(context).load(list[position].img).into(holder.binding.imageView)

    }

    override fun getItemCount(): Int {
       return list.size
    }
}