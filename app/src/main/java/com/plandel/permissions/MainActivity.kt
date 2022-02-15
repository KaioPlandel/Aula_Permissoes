package com.plandel.permissions
import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import com.plandel.permissions.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        binding.buttonChange.setOnClickListener{
            
            permisionList()
        }
    }
    
    fun hasWriteExternalStorage() =
        ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    
    fun hasCoarseLocation() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    
    fun hasFineLocation() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    
    fun permisionList(){
        var permissions = mutableListOf<String>()
        
        if(!hasWriteExternalStorage()){
            permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if(!hasCoarseLocation()){
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if(!hasFineLocation()){
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        
        ActivityCompat.requestPermissions(this,permissions.toTypedArray(),0)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        if(requestCode == 0 && grantResults.isNotEmpty()){
            for(i in grantResults.indices){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    Log.i(TAG, "onRequestPermissionsResult: Ok")
                }
            }
        }
    }
    

}