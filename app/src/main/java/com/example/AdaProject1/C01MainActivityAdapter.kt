package com.example.AdaProject1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.AdaProject1.domain.CDownloadData
import com.example.AdaProject1.domain.CDownloadList
import com.example.adap1.R
import com.example.adap1.databinding.W01mainDownloadListLayoutAdaptorBinding


class C01MainActivityAdapter(private val dataList: List<CDownloadList>) : RecyclerView.Adapter<C01MainActivityAdapter.ViewHolder>() {

    class ViewHolder(val binding: W01mainDownloadListLayoutAdaptorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CDownloadList) {

            binding.otv02NameDownload.text = data.tName
            binding.otv02DateAndTime.text = data.tDateTime
            binding.ocb02SelectData.isChecked = data.bSelect
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = W01mainDownloadListLayoutAdaptorBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)
        if(position % 2 == 0){

        } else{
            holder.binding.background.setBackgroundColor( Color.WHITE )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}