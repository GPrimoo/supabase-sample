package com.gprimo.supabase_sample.presentation.add

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
import com.gprimo.supabase_sample.R
import com.gprimo.supabase_sample.databinding.FragmentAddBinding
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.presentation.list.ListFragmentDirections
import com.gprimo.supabase_sample.util.addMenuProvider
import org.koin.android.ext.android.inject

class AddFragment : Fragment() {
    private val binding: FragmentAddBinding by lazy {
        FragmentAddBinding.inflate(layoutInflater)
    }

    private val viewModel: AddViewModel by inject()

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

        stateObserve()
        setupClickListener()
    }

    private fun stateObserve() = viewModel.state.observe(viewLifecycleOwner) {
        it?.let { state ->
            showLoading(state.loading)
            showError(state.error)
            hasSuccess(state.success)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loading.root.isVisible = isLoading
    }

    private fun showError(hasError: Boolean) {
        binding.error.root.isVisible = hasError
    }

    private fun setupClickListener() = with(binding) {
        btAdd.setOnClickListener {
            val contact = ContactModel(
                name = etName.text.toString(),
                email = etEmail.text.toString(),
                telephone = etPhone.text.toString()
            )

            viewModel.createContact(contact)
        }
    }

    private fun hasSuccess(success: Boolean) {
        if (success) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}