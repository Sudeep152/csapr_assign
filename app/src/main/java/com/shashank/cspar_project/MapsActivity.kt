package com.shashank.cspar_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.shashank.cspar_project.databinding.ActivityMapsBinding
import com.shashank.cspar_project.repository.repo
import com.shashank.cspar_project.viewModelFactory.MainViewModelFactory
import com.shashank.cspar_project.viewmodel.Mainviewmodel

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
      var long:Double = 0.000
      var lat:Double = 0.0000

    private lateinit var binding: ActivityMapsBinding
    lateinit var viewmodel: Mainviewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val repository= repo()
        val viewModelFactory = MainViewModelFactory(repository)
        viewmodel= ViewModelProvider(this,viewModelFactory)
            .get(Mainviewmodel::class.java)
        viewmodel.getAllData()
        viewmodel.myresponse.observe(this, Observer { respons ->

            if(respons.isSuccessful){
                val responseData = respons.body()

                val header = responseData?.location
                lat  = header?.lat?.toDouble()!!
                long  = header?.long?.toDouble()!!




             //  Toast.makeText(this, "$attributeName", Toast.LENGTH_SHORT).show()
               Toast.makeText(this, "($lat,$long)", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this, respons.code(), Toast.LENGTH_SHORT).show()
            }


        })


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(lat,long)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}