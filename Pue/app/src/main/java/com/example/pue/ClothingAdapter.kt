package com.example.pue

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.pue.data.DataSource
import com.example.pue.model.Cloth


/**
 * Adapter for the [RecyclerView] in [MainActivity].
 */
class ClothingAdapter(private val context: Context?):
    RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder>() {

    val dataset = DataSource.clothes
    var chosenClothes: MutableList<Cloth> = mutableListOf()
    var chosenClothesData = DataSource.chosenClothesData

    /**
     * Provides a reference for the views needed to display items in your list.
     */
    class ClothingViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.cloth_image)
        val cardItem = view.findViewById<com.google.android.material.card.MaterialCardView>(R.id.card_item)
        val clothName = view.findViewById<TextView>(R.id.cloth_name)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    /**
     * Creates new views with R.layout.item_view as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothingViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = DressingAdapter
        return ClothingViewHolder(layout)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: ClothingViewHolder, position: Int) {
        val resources = context?.resources
        val clothItem = dataset[position]
        holder.clothName.text = clothItem.name
        holder.imageView.setImageResource(clothItem.imageResourceId)
        holder.cardItem.isChecked = clothItem.checked
        holder.cardItem.setOnClickListener {
            if (chosenClothesData.contains(clothItem)) {
                //remove item from list
                chosenClothesData.remove(clothItem)

            }
            else chosenClothesData.add(clothItem)
            holder.cardItem.setChecked(!holder.cardItem.isChecked)
            clothItem.checked = !clothItem.checked
           // val context = holder.view.context
            // val intent = Intent(context, DressingActivity::class.java)
           // intent.putExtra(DressingActivity.LETTER, holder.clothName.text.toString())
           // context.startActivity(intent)

        }
    }

    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}