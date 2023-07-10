package com.gprimo.supabase_sample.presentation.edit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gprimo.supabase_sample.R
import com.gprimo.supabase_sample.databinding.FragmentEditBinding
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.util.addMenuProvider
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class EditFragment : Fragment() {

    private val binding: FragmentEditBinding by lazy {
        FragmentEditBinding.inflate(layoutInflater)
    }

    private val args: EditFragmentArgs by navArgs()

    private val viewModel: EditViewModel by inject {
        parametersOf(args.contact)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addMenuProvider(
            onCreateMenu = { menu: Menu, menuInflater: MenuInflater ->
                menuInflater.inflate(R.menu.default_menu, menu)
            }, onMenuItemSelected = { menu: MenuItem ->
                when (menu.itemId) {
                    android.R.id.home -> {
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                        true
                    }
                    else -> {
                        false
                    }
                }
            },
            owner = viewLifecycleOwner,
            toolbar = binding.toolbar,
            setDisplayHome = true
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()
        stateObserve()
    }

    private fun stateObserve() = viewModel.state.observe(viewLifecycleOwner) {
        it?.let { state ->
            setupFields(state.contact)
            showLoading(state.loading)
            showError(state.error)
            setupClickListener(state.contact)
            if (state.success) {
                goBack(state.contact)
            }
        }
    }

    private fun setupFields(contactModel: ContactModel?) = with(binding) {
        contactModel?.let { contact ->
            etName.setText(contact.name)
            etEmail.setText(contact.email)
            etPhone.setText(contact.telephone)
        }
    }

    private fun setupClickListener(contactModel: ContactModel?) = with(binding) {
        binding.btAdd.setOnClickListener {
            contactModel?.let { contact ->
                val newContact = contact.copy(
                    name = etName.text.toString(),
                    email = etEmail.text.toString(),
                    telephone = etPhone.text.toString()
                )
                viewModel.editContact(newContact)
            } ?: run {
                showError(true)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loading.root.isVisible = isLoading
    }

    private fun showError(hasError: Boolean) {
        binding.error.root.isVisible = hasError
    }

    private fun goBack(contactModel: ContactModel?) = contactModel?.let { contact ->
        val action = EditFragmentDirections.actionEditFragmentToViewFragment(contact)
        findNavController().navigate(action)
        findNavController().popBackStack(R.id.editFragment, true)
    }
}