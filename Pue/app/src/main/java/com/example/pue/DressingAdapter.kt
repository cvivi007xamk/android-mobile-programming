package com.example.pue

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
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

/**
 * Adapter for the [RecyclerView] in [DressingActivity].
 */
class DressingAdapter(private val letterId: String, context: Context) :
    RecyclerView.Adapter<DressingAdapter.DressViewHolder>() {

    var chosenClothesData = DataSource.chosenClothesData

    //Järjestetään valitut vaatteet pukemisjärjestykseen
    val sortedChosenClothesData = chosenClothesData.sortedBy { it.order }
    private val filteredWords: List<String>


    // Seuraaavaa ei tarvita?
    init {
        // Retrieve the list of words from res/values/arrays.xml
        val words = context.resources.getStringArray(R.array.words).toList()

        filteredWords = words
            // Returns items in a collection if the conditional clause is true,
            // in this case if an item starts with the given letter,
            // ignoring UPPERCASE or lowercase.
            .filter { it.startsWith(letterId, ignoreCase = true) }
            // Returns a collection that it has shuffled in place
            .shuffled()
            // Returns the first n items as a [List]
            .take(5)
            // Returns a sorted version of that [List]
            .sorted()
    }
    // Näitä taas tarvitaan

    class DressViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val cardItem = view.findViewById<com.google.android.material.card.MaterialCardView>(R.id.card_item)
        val clothName = view.findViewById<TextView>(R.id.cloth_name)
        val imageView: ImageView = view.findViewById(R.id.cloth_image)
    }

    override fun getItemCount(): Int = sortedChosenClothesData.size

    /**
     * Creates new views with R.layout.item_view as its template
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DressViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = Accessibility
        return DressViewHolder(layout)
    }

    /**
     * Replaces the content of an existing view with new data
     */
    override fun onBindViewHolder(holder: DressViewHolder, position: Int) {

        val item = sortedChosenClothesData[position]
        // Needed to call startActivity
        val context = holder.view.context

        // Set the text of the WordViewHolder
        holder.clothName.text = item.name
        holder.imageView.setImageResource(item.imageResourceId)

        holder.cardItem.setOnClickListener {
           // Add code to flip the card
        }
// How to implicit intent to Google
        //holder.button.setOnClickListener {
           // val queryUrl: Uri = Uri.parse("${DressingActivity.SEARCH_PREFIX}${item}")
           // val intent = Intent(Intent.ACTION_VIEW, queryUrl)
           // context.startActivity(intent)

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
            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}