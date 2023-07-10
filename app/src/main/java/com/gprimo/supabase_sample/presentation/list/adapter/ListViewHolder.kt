package com.gprimo.supabase_sample.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.gprimo.supabase_sample.databinding.ListContactsBinding
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.util.OnContactClicked

class ListViewHolder(
    private val binding: ListContactsBinding,
    private val onContactClicked: OnContactClicked
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(list: List<ContactModel>, position: Int) {
        val currentContact = list.getOrNull(position)
        val last = position == list.size - ONE
        setupView(currentContact, last)
    }

    private fun setupView(contact: ContactModel?, last: Boolean) = contact?.let {
        binding.tvName.text = contact.name
        binding.root.setOnClickListener {
            onContactClicked(contact)
        }
        binding.divider.isVisible = last.not()
    }

    companion object {
        fun newInstance(
            viewGroup: ViewGroup,
            onContactClicked: OnContactClicked
        ) = ListViewHolder(
            binding = ListContactsBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            ),
            onContactClicked
        )

        const val ONE = 1
    }
}