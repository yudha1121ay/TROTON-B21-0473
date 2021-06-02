package com.bangkit.maskcam.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.maskcam.R

class IntroAdapter(private val introEntity: List<IntroEntity>) : RecyclerView.Adapter<IntroAdapter.IntroHolder>()
    {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroHolder {
            return IntroHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.intro_item,
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: IntroHolder, position: Int) {
            holder.bind(introEntity[position])
        }

        override fun getItemCount(): Int {
            return introEntity.size
        }

        inner class IntroHolder(view: View) : RecyclerView.ViewHolder(view){

        private val introImage = view.findViewById<ImageView>(R.id.introImage)
        private val introTitle = view.findViewById<TextView>(R.id.introTitle)
        private val introDesc = view.findViewById<TextView>(R.id.introDesc)

        fun bind(introEntity: IntroEntity){
            introImage.setImageResource(introEntity.image)
            introTitle.text = introEntity.title
            introDesc.text = introEntity.desc
        }
    }
}