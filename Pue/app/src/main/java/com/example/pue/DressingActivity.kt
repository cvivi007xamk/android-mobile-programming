package com.example.pue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pue.databinding.ActivityDressingBinding
import android.util.DisplayMetrics
import android.content.Context
import android.content.Intent


class DressingActivity : AppCompatActivity() {
    companion object {
        const val NUMBER = "number"
        const val SEARCH_PREFIX = "https://www.google.fi/search?q="
    }
    private lateinit var recyclerView: RecyclerView
    private lateinit var listIntent: Intent

    //Variable to handle grid/linear layout state. This is changed from the menu button.
    private var isLinearLayoutManager = false

    //code from https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
    // This autofits gridlayout columns with the screen size (and orientation). columnWidth is passsed in as parameter when called.

    private fun RecyclerView.autoFitColumns(columnWidth: Int) {
        val displayMetrics = this.context.resources.displayMetrics
        val noOfColumns = ((displayMetrics.widthPixels / displayMetrics.density) / columnWidth).toInt()
        this.layoutManager = GridLayoutManager(this.context, noOfColumns)
    }

    // Function to set layoutmanager (grid/linear) depending on the state (value of isLinearLayoutManager)
    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            //recyclerView.layoutManager = GridLayoutManager(this, 4)
            // Let's use the function found from stackoverflow here instead of passing an integer as spanCount.
            recyclerView.autoFitColumns(180)

        }
        recyclerView.adapter = DressingAdapter(this)
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

        // Retrieve a binding object that allows you to refer to views by id name
        val binding = ActivityDressingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Retrieve the LETTER from the Intent extras
        val numberOfClothes = intent?.extras?.getString(NUMBER).toString()


        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = DressingAdapter(this)

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        // Sets the GridLayoutManager of the recyclerview on start
        chooseLayout()

        title = getString(R.string.detail_prefix) + " " + numberOfClothes + " " + getString(R.string.kpl)
    }
}