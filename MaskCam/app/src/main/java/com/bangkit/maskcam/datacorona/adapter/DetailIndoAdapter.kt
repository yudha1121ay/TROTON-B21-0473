package com.bangkit.maskcam.datacorona.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.maskcam.R
import com.bangkit.maskcam.datacorona.entity.ProvinceEntity
import kotlinx.android.synthetic.main.list_item.view.*

class DetailIndoAdapter(private val list: ArrayList<ProvinceEntity>): RecyclerView.Adapter<DetailIndoAdapter.DataViewHolder>() {
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(province: ProvinceEntity) {
            with(itemView) {
                namaTempat.text = province.attributes.Provinsi
                positif.text = province.attributes.provPosi.toString()
                meninggal.text = province.attributes.provMeni.toString()
                sembuh.text = province.attributes.provSemb.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}