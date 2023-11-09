package com.example.comics.ui.comics.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.comics.databinding.ItemListBinding
import com.example.comics.domain.entity.ItemVO

internal class ItemViewHolder(
    private val itemBinding: ItemListBinding
) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(
        item: ItemVO,
    ) = with(itemBinding) {

        Glide.with(itemBinding.root)
            .load(item.image)
            .centerCrop()
            .into(itemBinding.actionImage)

        actionTitle.text = item.title
        actionSubTitle.text = item.subtitle
    }
}
