package com.bangkit.maskcam.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.maskcam.R
import com.bangkit.maskcam.api.ApiConfig
import com.bangkit.maskcam.databinding.ActivityHomeBinding
import com.bangkit.maskcam.datacorona.entity.CoronaWorldEntity
import com.bangkit.maskcam.datacorona.entity.IndonesiaEntity
import com.bangkit.maskcam.datacorona.activity.DetailCoronaIndo
import com.bangkit.maskcam.datacorona.activity.DetailCoronaWorld
import com.bangkit.maskcam.settings.SettingsActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import kotlinx.android.synthetic.main.activity_detail_corona.*
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        showDataIndo()
        showDataDunia()


        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel(R.drawable.carousel1))
        imageList.add(SlideModel(R.drawable.carousel2))
        imageList.add(SlideModel(R.drawable.carousel3))
        imageList.add(SlideModel(R.drawable.carousel4))

        val imageSlider = findViewById<ImageSlider>(R.id.carousel)
        imageSlider.setImageList(imageList)

        btnSettings.setOnClickListener {
            Intent(this@HomeActivity, SettingsActivity::class.java).also {
                startActivity(it)
            }
        }

        btnDetailIndo.setOnClickListener {
            Intent(this@HomeActivity, DetailCoronaIndo::class.java).also {
                startActivity(it)
            }
        }
        btnDetailDunia.setOnClickListener {
            Intent(this@HomeActivity, DetailCoronaWorld::class.java).also {
                startActivity(it)
            }
        }
    }

    private fun showDataDunia() {
        ApiConfig.instance.getDuniaPositif().enqueue(object : Callback<CoronaWorldEntity> {
            override fun onResponse(
                call: Call<CoronaWorldEntity>,
                response: Response<CoronaWorldEntity>
            ) {
                val list = response.body()
                val positif = list?.value

                positifDunia.text = positif
            }

            override fun onFailure(
                call: Call<CoronaWorldEntity>, t: Throwable
            ) {
                Toast.makeText(this@HomeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDataIndo() {
        ApiConfig.instance.getCoronaIndo().enqueue(object : Callback<ArrayList<IndonesiaEntity>> {
            override fun onResponse(
                call: Call<ArrayList<IndonesiaEntity>>,
                response: Response<ArrayList<IndonesiaEntity>>
            ) {
                val list = response.body()?.get(0)
                val positif = list?.positif

                positifIndo.text = positif
            }

            override fun onFailure(
                call: Call<ArrayList<IndonesiaEntity>>, t: Throwable
            ) {
                Toast.makeText(this@HomeActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
