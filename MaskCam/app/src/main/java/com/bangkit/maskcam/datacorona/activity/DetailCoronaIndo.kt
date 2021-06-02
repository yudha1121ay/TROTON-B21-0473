package com.bangkit.maskcam.datacorona.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.maskcam.api.ApiConfig
import com.bangkit.maskcam.databinding.ActivityDetailCoronaBinding
import com.bangkit.maskcam.datacorona.entity.ProvinceEntity
import com.bangkit.maskcam.datacorona.adapter.DetailIndoAdapter
import kotlinx.android.synthetic.main.activity_detail_corona.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCoronaIndo : AppCompatActivity() {

    private var _binding: ActivityDetailCoronaBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityDetailCoronaBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        showProvence()
    }

    private fun showProvence() {
        listCovidIndo.setHasFixedSize(true)
        listCovidIndo.layoutManager = LinearLayoutManager(this)

        ApiConfig.instance.getCoronaProv().enqueue(object : Callback<ArrayList<ProvinceEntity>>{
            override fun onResponse(
                call: Call<ArrayList<ProvinceEntity>>,
                response: Response<ArrayList<ProvinceEntity>>
            ) {
                val list = response.body()
                val adapter = list?.let { DetailIndoAdapter(it) }
                listCovidIndo.adapter = adapter
            }

            override fun onFailure(
                call: Call<ArrayList<ProvinceEntity>>, t: Throwable
            ) {
                Toast.makeText(this@DetailCoronaIndo, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}