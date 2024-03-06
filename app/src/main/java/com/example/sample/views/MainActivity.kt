package com.example.sample.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.sample.R
import com.example.sample.databinding.ActivityMainBinding
import com.example.sample.network.ApiResult
import com.example.sample.repositories.Repository
import com.example.sample.viewModelFactory.MainViewModelFactory
import com.example.sample.viewmodels.MainActivityViewModels
import com.google.gson.Gson
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mContentViewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContentViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val mainActivityViewModels: MainActivityViewModels = ViewModelProvider(
            this, MainViewModelFactory(
                Repository()
            )
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
                        mContentViewBinding.txtData.text = Gson().toJson(it.data)
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