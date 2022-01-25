package com.shashank.cspar_project.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shashank.cspar_project.model.ApiResponse
import com.shashank.cspar_project.repository.repo
import kotlinx.coroutines.launch
import retrofit2.Response

class Mainviewmodel(val repository: repo): ViewModel() {

    val myresponse : MutableLiveData<Response<ApiResponse>> = MutableLiveData()
    fun  getAllData() {
        viewModelScope.launch {

            val response = repository.getAllData()
            myresponse.value = response

        }
    }



}