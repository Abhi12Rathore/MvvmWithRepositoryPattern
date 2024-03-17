package com.example.sample.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sample.R
import com.example.sample.databinding.AdapterItemBinding
import com.example.sample.models.ProductsData
import java.lang.Exception

class MainActivityAdapter(
    private val mContext: Context,
    private val mDataList: List<ProductsData>
) : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {

    class ViewHolder(val adapterItemBinding: AdapterItemBinding) :
        RecyclerView.ViewHolder(adapterItemBinding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterItemBinding = DataBindingUtil.inflate<AdapterItemBinding>(
            LayoutInflater.from(mContext),
            R.layout.adapter_item,
            parent,
            false
        )
        return ViewHolder(adapterItemBinding)
    }

    override fun getItemCount() = mDataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.adapterItemBinding.data = mDataList[position]
        try {
            Glide.with(mContext).load(mDataList[position].image)
                .into(holder.adapterItemBinding.imgView)
        } catch (e: Exception) {
        }
        holder.adapterItemBinding.executePendingBindings()
    }

}