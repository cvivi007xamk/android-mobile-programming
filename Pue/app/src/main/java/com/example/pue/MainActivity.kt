package com.example.pue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pue.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    //Variable to handle grid/linear layout state. This is changed from the menu button.
    private var isLinearLayoutManager = true

    // Function to set layoutmanager (grid/linear) depending on the state (value of isLinearLayoutManager)
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 4)
        }
        recyclerView.adapter = ClothingAdapter(this)
    }
    // Function to change the menu icon drawable depending on isLinearLayoutManager
    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        if (isLinearLayoutManager)
             menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_layout)
         else menuItem.icon = ContextCompat.getDrawable(this, R.drawable.ic_linear_layout)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        // Calls code to set the icon based on the LinearLayoutManager of the RecyclerView
        setIcon(layoutButton)
        return true
    }

    // Function to handle clicks on menu button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                // Sets isLinearLayoutManager (a Boolean) to the opposite value
                isLinearLayoutManager = !isLinearLayoutManager
                // Sets layout and icon
                chooseLayout()
                setIcon(item)
                return true
            }
            //  Otherwise, do nothing and use the core event handling
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        // Sets the LinearLayoutManager of the recyclerview
        chooseLayout()

    }
}