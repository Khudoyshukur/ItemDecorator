package com.example.itemdecorator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.itemdecorator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val data = listOf(
        "Android",
        "IOS",
        "Windows",
        "Linux",
        "Ubuntu",
        "Fedore",
        "Kali",
        "Something",
        "Debian",
        "Android",
        "IOS",
        "Windows",
        "Linux",
        "Ubuntu",
        "Fedore",
        "Kali",
        "Something",
        "Debian",
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val decoration = VerticalFirstDividerDecoration(this).also {
            it.setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.divider, null))
        }
        binding.recyclerView.addItemDecoration(decoration)
        binding.recyclerView.adapter = Adapter().also {
            it.submitList(data)
        }
    }
}