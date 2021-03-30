package ikt205.project.mylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ikt205.project.mylist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.listEntryListing


    }
}