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

    class ViewHolder(val oBinding: W01mainDownloadListLayoutAdaptorBinding) : RecyclerView.ViewHolder(oBinding.root) {
        fun bind(paData: CDownloadList) {

            oBinding.otv02NameDownload.text = paData.tName
            oBinding.otv02DateAndTime.text = paData.tDateTime
            oBinding.ocb02SelectData.isChecked = paData.bSelect
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val oInflater = LayoutInflater.from(parent.context)
        val oBinding = W01mainDownloadListLayoutAdaptorBinding.inflate(oInflater, parent, false)
        return ViewHolder(oBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val oData = dataList[position]
        holder.bind(oData)
        if(position % 2 == 0){

        } else{
            holder.oBinding.background.setBackgroundColor( Color.WHITE )
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}