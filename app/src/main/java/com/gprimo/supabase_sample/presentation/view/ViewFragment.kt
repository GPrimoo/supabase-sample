package com.gprimo.supabase_sample.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.gprimo.supabase_sample.R
import com.gprimo.supabase_sample.component.fieldview.FieldViewData
import com.gprimo.supabase_sample.databinding.FragmentViewBinding
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.util.addMenuProvider
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ViewFragment : Fragment() {
    private val binding: FragmentViewBinding by lazy {
        FragmentViewBinding.inflate(layoutInflater)
    }

    private val args: ViewFragmentArgs by navArgs()

    private val viewModel: ViewViewModel by inject {
        parametersOf(args.contact)
    }

    private var contactModel: ContactModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {addMenuProvider(
        onCreateMenu = { menu: Menu, menuInflater: MenuInflater ->
            menuInflater.inflate(R.menu.view_menu, menu)
        }, onMenuItemSelected = { menu: MenuItem ->
            when (menu.itemId) {
                R.id.action_edit -> {
                    goToEdit()
                    true
                }
                R.id.action_delete -> {
                    deleteContact()
                    true
                }
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
            showLoading(state.loading)
            showError(state.error)
            showDetails(state.contact)
            deleteSuccess(state.success)
            this@ViewFragment.contactModel = state.contact
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loading.root.isVisible = isLoading
    }

    private fun showError(hasError: Boolean) {
        binding.error.root.isVisible = hasError
    }

    private fun showDetails(contactModel: ContactModel?) = with(binding) {
        contactModel?.let { contact ->
            fvName.setup(
                FieldViewData(
                    label = getString(R.string.name),
                    value = contact.name
                )
            )
            fvEmail.setup(
                FieldViewData(
                    label = getString(R.string.email),
                    value = contact.email
                )
            )
            fvPhone.setup(
                FieldViewData(
                    label = getString(R.string.phone),
                    value = contact.telephone.toString()
                )
            )
        }
    }

    private fun goToEdit() = this.contactModel?.let {  contact ->
        val action = ViewFragmentDirections.actionViewFragmentToEditFragment(contact)
        findNavController().navigate(action)
    }

    private fun deleteContact() = this.contactModel?.let { contact ->
        viewModel.deleteContact(contact)
    }

    private fun deleteSuccess(hasSuccess: Boolean) {
        if (hasSuccess) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }
}