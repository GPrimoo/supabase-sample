package com.gprimo.supabase_sample.presentation.list.adapter

import androidx.recyclerview.widget.DiffUtil
import com.gprimo.supabase_sample.model.ContactModel

class ListDiffCallback: DiffUtil.ItemCallback<ContactModel>() {
    override fun areContentsTheSame(oldItem: ContactModel, newItem: ContactModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: ContactModel, newItem: ContactModel) = oldItem.id == newItem.id
}