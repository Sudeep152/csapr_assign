package com.shashank.cspar_project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.shashank.cspar_project.MapsActivity
import com.shashank.cspar_project.R
import com.shashank.cspar_project.adapter.apiAdapter
import com.shashank.cspar_project.repository.repo
import com.shashank.cspar_project.viewModelFactory.MainViewModelFactory
import com.shashank.cspar_project.viewmodel.Mainviewmodel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: Mainviewmodel

    private val myAdapter by lazy { apiAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this,MapsActivity::class.java))
        }


        setupRecyclerview()
        val repository= repo()
        val viewModelFactory = MainViewModelFactory(repository)
        viewmodel= ViewModelProvider(this,viewModelFactory)
            .get(Mainviewmodel::class.java)

        viewmodel.getAllData()
        viewmodel.myresponse.observe(this, Observer { respons ->

            if(respons.isSuccessful){
                respons.body()?.let { myAdapter.setData(it) }
            }else {
                Toast.makeText(this, respons.code(), Toast.LENGTH_SHORT).show()
            }


        })

    }
    private fun setupRecyclerview() {
        rv.adapter = myAdapter
        rv.layoutManager = LinearLayoutManager(this)
    }
}