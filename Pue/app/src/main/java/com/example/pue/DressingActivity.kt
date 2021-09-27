package com.example.pue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pue.databinding.ActivityDressingBinding

class DressingActivity : AppCompatActivity() {
    companion object {
        const val NUMBER = "number"
        const val SEARCH_PREFIX = "https://www.google.fi/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve a binding object that allows you to refer to views by id name
        val binding = ActivityDressingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Retrieve the chosenClothes list


        // Retrieve the LETTER from the Intent extras
        val numberOfClothes = intent?.extras?.getString(NUMBER).toString()


        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DressingAdapter(numberOfClothes, this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + numberOfClothes + " " + getString(R.string.kpl)
    }
}