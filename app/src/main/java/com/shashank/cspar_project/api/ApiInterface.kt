package com.shashank.cspar_project.api

import com.shashank.cspar_project.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface   ApiInterface {


    @GET("/hrportal/public/tracking/viewreport")
 suspend fun getReport():Response<ApiResponse>
}