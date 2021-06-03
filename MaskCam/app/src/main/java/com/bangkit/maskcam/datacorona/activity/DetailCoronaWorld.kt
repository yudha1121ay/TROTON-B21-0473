package com.bangkit.maskcam.datacorona.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.maskcam.api.ApiConfig
import com.bangkit.maskcam.databinding.ActivityDetailCoronaWorldBinding
import com.bangkit.maskcam.datacorona.adapter.DetailDuniaAdapter
import com.bangkit.maskcam.datacorona.entity.CoronaWorld
import kotlinx.android.synthetic.main.activity_detail_corona.*
import kotlinx.android.synthetic.main.activity_detail_corona_world.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailCoronaWorld : AppCompatActivity() {

    private var _binding: ActivityDetailCoronaWorldBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailCoronaWorldBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        showWorld()
    }

    private fun showWorld() {
        listCovidDunia.setHasFixedSize(true)
        listCovidDunia.layoutManager = LinearLayoutManager(this)

        ApiConfig.instance.getCoronaWorld().enqueue(object : Callback<ArrayList<CoronaWorld>> {
            override fun onResponse(
                call: Call<ArrayList<CoronaWorld>>,
                response: Response<ArrayList<CoronaWorld>>
            ) {
                val list = response.body()
                val adapter = list?.let { DetailDuniaAdapter(it) }
                listCovidDunia.adapter = adapter
            }

            override fun onFailure(
                call: Call<ArrayList<CoronaWorld>>, t: Throwable
            ) {
                Toast.makeText(this@DetailCoronaWorld, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}