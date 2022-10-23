package com.rifara.praktik3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    // FragmentManager yang merupakan antarmuka untuk mengorganisir objek fragment yang terdapat didalam sebuah activity.
        val mFragmentManager = supportFragmentManager
        val mHomeFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        if (fragment !is HomeFragment){
            Log.d("MyFlefibleFragment", "Fragment Name : " + HomeFragment::class.java.simpleName)
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container,mHomeFragment, HomeFragment::class.java.simpleName)
                .commit()
// Metode .beginTransaction() untuk memulai proses perubahan.
// Metode add() akan menambahkan objek fragment ke dalam layout container.
// Metode .commit() di atas akan mengeksekusi untuk melakukan pemasangan objek secara asynchronous untuk ditampilkan ke antar muka pengguna (user interface).

        }


    }
}