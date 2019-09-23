package com.pandulapeter.debugMenu.views.items.text

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pandulapeter.debugMenu.R

internal class TextViewHolder(root: View) : RecyclerView.ViewHolder(root) {

    private val textView = itemView.findViewById<TextView>(R.id.text)

    fun bind(viewModel: TextViewModel, textColor: Int) {
        textView.text = viewModel.text
        textView.setTypeface(textView.typeface, if (viewModel.isTitle) Typeface.BOLD else Typeface.NORMAL)
        textView.setTextColor(textColor)
    }

    companion object {
        fun create(parent: ViewGroup) =
            TextViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false))
    }
}