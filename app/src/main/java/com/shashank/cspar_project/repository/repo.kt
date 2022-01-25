package com.shashank.cspar_project.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shashank.cspar_project.RetrofitInstance
import com.shashank.cspar_project.api.ApiInterface
import com.shashank.cspar_project.model.ApiResponse
import com.shashank.cspar_project.model.Location
import retrofit2.Response

class repo {


   suspend fun getAllData() :Response<ApiResponse>{
       return RetrofitInstance.api.getReport()
   }

}