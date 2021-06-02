package com.bangkit.maskcam.datacorona.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.maskcam.R
import com.bangkit.maskcam.datacorona.entity.CoronaWorld
import kotlinx.android.synthetic.main.list_item.view.*

class DetailDuniaAdapter(private val list: ArrayList<CoronaWorld>): RecyclerView.Adapter<DetailDuniaAdapter.DataViewHolder>() {
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(world: CoronaWorld) {
            with(itemView) {
                namaTempat.text = world.attributes.region
                positif.text = world.attributes.Confirmed.toString()
                meninggal.text = world.attributes.Deaths.toString()
                sembuh.text = world.attributes.Recovered.toString()
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