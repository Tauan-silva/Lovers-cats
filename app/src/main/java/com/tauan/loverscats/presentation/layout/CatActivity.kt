package com.tauan.loverscats.presentation.layout

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tauan.loverscats.R
import com.tauan.loverscats.adapter.AdapterCat
import com.tauan.loverscats.databinding.ActivityCatBinding
import com.tauan.loverscats.viewmodel.CatViewModel
import kotlinx.coroutines.launch

class CatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCatBinding
    private lateinit var adapterCat: AdapterCat
    private var mProgressDialog: Dialog? = null
    private lateinit var mViewModel: CatViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //toolbar
        val toolbar = binding.toolBarCat.root
        setSupportActionBar(toolbar)
        //viewModel
        mViewModel = ViewModelProvider(this)
            .get(CatViewModel::class.java)
        mViewModel.viewModelScope.launch {
            mViewModel.getImageCat()

            catImageObserver()

            setupRecyclerView()
        }

    }

    private fun catImageObserver(){
        mViewModel.resultCat.observe(this){resultCat ->
            resultCat?.let {
                adapterCat.differ.submitList(it.data)
            }
        }

        mViewModel.loadingResultCat.observe(this){load ->
            load.let {
                if(load){
                    showProgressDialog()
                }else {
                    hideProgressDialog()
                }
            }
        }

        mViewModel.errorResultCat.observe(this){errorCode ->
            errorCode.let {
                Toast.makeText(this, "erro $errorCode", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun setupRecyclerView(){
        adapterCat = AdapterCat()
        binding.recyclerCat.apply {
            adapter = adapterCat
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)

        }
    }

    private fun showProgressDialog(){
        mProgressDialog = Dialog(this)
        mProgressDialog?.let {
            it.setContentView(R.layout.dialog_layout)
            it.show()
        }
    }

    private fun hideProgressDialog() = mProgressDialog?.dismiss()
}