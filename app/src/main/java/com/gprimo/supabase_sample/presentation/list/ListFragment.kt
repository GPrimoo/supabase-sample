package com.gprimo.supabase_sample.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gprimo.supabase_sample.R
import com.gprimo.supabase_sample.databinding.FragmentListBinding
import com.gprimo.supabase_sample.model.ContactModel
import com.gprimo.supabase_sample.presentation.list.adapter.ListContactAdapter
import com.gprimo.supabase_sample.util.addMenuProvider
import org.koin.android.ext.android.inject

class ListFragment : Fragment() {

    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private val viewModel: ListViewModel by inject()

    private val listAdapter: ListContactAdapter by lazy {
        ListContactAdapter(
            onContactClicked = ::onContactClicked
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addMenuProvider(
            onCreateMenu = { menu: Menu, menuInflater: MenuInflater ->
                menuInflater.inflate(R.menu.list_menu, menu)
            }, onMenuItemSelected = { menu: MenuItem ->
                when (menu.itemId) {
                    R.id.action_new -> {
                        val action = ListFragmentDirections.actionListFragmentToAddFragment()
                        findNavController().navigate(action)
                        true
                    }

                    else -> {
                        false
                    }
                }
            },
            owner = viewLifecycleOwner,
            toolbar = binding.toolbar
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.init()
        setupRecycler()
        stateObserve()
    }

    private fun setupRecycler() = with(binding.recycler) {
        val linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager
        adapter = listAdapter
    }

    private fun stateObserve() = viewModel.state.observe(viewLifecycleOwner) {
        it?.let { state ->
            showLoading(state.loading)
            showError(state.error)
            updateList(state.list)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loading.root.isVisible = isLoading
    }

    private fun showError(hasError: Boolean) {
        binding.error.root.isVisible = hasError
    }

    private fun updateList(list: List<ContactModel>) {
        listAdapter.submitList(list)
    }

    private fun onContactClicked(contact: ContactModel) {
        val action = ListFragmentDirections.actionListFragmentToViewFragment(contact)
        findNavController().navigate(action)
    }
}