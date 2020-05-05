package com.eventum.ma.views.ui.activities

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.eventum.ma.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    val latitude =4.635990452289505
    val longitude = -74.08326288598688

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
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
        val zoom = 16f
        val centerMap = LatLng(latitude, longitude)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))
        val markerOptions = MarkerOptions()
        markerOptions.position(centerMap)
        val bitmapDraw = this.applicationContext?.let { ContextCompat.getDrawable(it, R.drawable.eventum_logo) } as BitmapDrawable
        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 100, 150, false)

        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        googleMap?.addMarker(markerOptions)
        googleMap?.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.custom_map))
    }
}
