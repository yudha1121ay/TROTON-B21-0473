package com.bangkit.maskcam.intro

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.maskcam.activity.HomeActivity
import com.bangkit.maskcam.R
import com.google.android.material.button.MaterialButton


class IntroActivity : AppCompatActivity() {

    private lateinit var introAdapter: IntroAdapter
    private lateinit var indicator: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        setIntroItems()
        indicator()
        currentIndicator(0)

        val pref = getSharedPreferences("ActivityPREF", MODE_PRIVATE)
        if (pref.getBoolean("activity_executed", false)) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val ed: SharedPreferences.Editor = pref.edit()
            ed.putBoolean("activity_executed", true)
            ed.apply()
        }
    }

    private fun setIntroItems(){
        introAdapter = IntroAdapter(
            listOf(
                IntroEntity(
                    R.drawable.slide_1,
                    "Selamat Datang",
                    "Deskripsi 1"
                ),
                IntroEntity(
                    R.drawable.slide_2,
                    "Back To School",
                    "Deskripsi 2"
                ),
                IntroEntity(
                    R.drawable.slide_3,
                    "Dapatkan Notifikasi",
                    "Deskripsi 3"
                ),
                IntroEntity(
                    R.drawable.slide_4,
                    "Lihat Live Data",
                    "Deskripsi 4"
                ),
                IntroEntity(
                    R.drawable.slide_5,
                    "Semua Sudah Siap",
                    "Deskripsi 5"
                )
            )
        )
        val introViewPager = findViewById<ViewPager2>(R.id.introViewPager)
        introViewPager.adapter = introAdapter
        introViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentIndicator(position)
            }
        })
        (introViewPager.getChildAt(0)as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        findViewById<ImageView>(R.id.btnNext).setOnClickListener {
            if(introViewPager.currentItem+1 < introAdapter.itemCount){
                introViewPager.currentItem += 1
            } else {
                IntentHome()
            }
        }
        findViewById<MaterialButton>(R.id.btnStart).setOnClickListener {
            IntentHome()
        }
    }

    private fun IntentHome(){
        startActivity(Intent(applicationContext, HomeActivity::class.java))
        finish()
    }

    private fun indicator(){
        indicator = findViewById(R.id.indicator)
        val indicators = arrayOfNulls<ImageView>(introAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_pager_unselected
                    )
                )
                it.layoutParams = layoutParams
                indicator.addView(it)
            }
        }
    }

    private fun currentIndicator(position: Int){
        val Count = indicator.childCount
        for (i in 0 until Count) {
            val imageView = indicator.getChildAt(i) as ImageView
            if (i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_pager_selected
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_pager_unselected
                    )
                )
            }
        }
    }
}