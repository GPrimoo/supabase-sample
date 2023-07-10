package com.gprimo.supabase_sample.presentation.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.util.OnContactClicked

class ListContactAdapter(
    private val onContactClicked: OnContactClicked
) : ListAdapter<ContactModel, ListViewHolder>(ListDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder.newInstance(parent, onContactClicked)

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(currentList, position)
}