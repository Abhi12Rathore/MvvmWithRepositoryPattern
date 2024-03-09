package com.example.sample.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample.MyApplication
import com.example.sample.R
import com.example.sample.databinding.ActivityMainBinding
import com.example.sample.network.ApiResult
import com.example.sample.repositories.Repository
import com.example.sample.viewModelFactory.MainViewModelFactory
import com.example.sample.viewmodels.MainActivityViewModels
import com.example.sample.views.adapter.MainActivityAdapter
import com.google.gson.Gson
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var mContentViewBinding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContentViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.title = "MVVM Demo"
        (application as MyApplication).appComponent.inject(this)

        val mainActivityViewModels: MainActivityViewModels = ViewModelProvider(
            this, viewModelFactory
        )[MainActivityViewModels::class.java]
        mainActivityViewModels.fetchProductData()

        lifecycleScope.launch {
            mainActivityViewModels.liveData.observe(this@MainActivity) {
                when (it) {
                    is ApiResult.Loading -> {
                        mContentViewBinding.loading = true
                    }

                    is ApiResult.Success -> {
                        mContentViewBinding.loading = false
                        mContentViewBinding.rvData.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = it.data?.let { data ->
                                MainActivityAdapter(
                                    this@MainActivity,
                                    data
                                )
                            }
                        }
                        //mContentViewBinding.txtData.text = Gson().toJson(it.data)
                    }

                    is ApiResult.Error -> {
                        mContentViewBinding.loading = false
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}