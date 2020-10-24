package com.pandulapeter.beagle.core.view.gallery.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.pandulapeter.beagle.core.R
import com.pandulapeter.beagle.core.util.extension.getScreenCapturesFolder
import com.pandulapeter.beagle.core.view.MediaView
import com.pandulapeter.beagle.utils.consume

internal class ImageViewHolder private constructor(
    itemView: View,
    onTap: (Int) -> Unit,
    onLongTap: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val mediaView = itemView.findViewById<MediaView>(R.id.beagle_media_view)
    private val onCheckChangeListener = CompoundButton.OnCheckedChangeListener { _, _ ->
        adapterPosition.let { adapterPosition ->
            if (adapterPosition != RecyclerView.NO_POSITION) {
                onLongTap(adapterPosition)
            }
        }
    }

    init {
        itemView.setOnClickListener {
            adapterPosition.let { adapterPosition ->
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onTap(adapterPosition)
                }
            }
        }
        itemView.setOnLongClickListener {
            consume {
                adapterPosition.let { adapterPosition ->
                    if (adapterPosition != RecyclerView.NO_POSITION) {
                        onLongTap(adapterPosition)
                    }
                }
            }
        }
    }

    fun bind(uiModel: UiModel) {
        mediaView.textView.text = uiModel.fileName
        mediaView.imageView.run { load(context.getScreenCapturesFolder().resolve(uiModel.fileName)) }
        mediaView.checkBox.setOnCheckedChangeListener(null)
        mediaView.checkBox.isChecked = uiModel.isSelected
        mediaView.checkBox.setOnCheckedChangeListener(onCheckChangeListener)
    }

    data class UiModel(
        val fileName: String,
        val isSelected: Boolean,
        override val lastModified: Long
    ) : GalleryListItem {
        override val id = fileName
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onTap: (Int) -> Unit,
            onLongTap: (Int) -> Unit
        ) = ImageViewHolder(
            itemView = LayoutInflater.from(parent.context).inflate(R.layout.beagle_item_gallery_image, parent, false),
            onTap = onTap,
            onLongTap = onLongTap
        )
    }
}