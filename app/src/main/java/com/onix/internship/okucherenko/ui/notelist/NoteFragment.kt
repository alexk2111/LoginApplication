package com.onix.internship.okucherenko.ui.notelist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.onix.internship.okucherenko.R
import com.onix.internship.okucherenko.arch.BaseFragment
import com.onix.internship.okucherenko.databinding.NoteFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteFragment : BaseFragment<NoteFragmentBinding>(R.layout.note_fragment) {
    override val viewModel: NoteViewModel by viewModel()
    private val adapter = NoteAdapter()

    override fun setObservers() {
        super.setObservers()

        binding.noteViewModel = viewModel
        viewModel.notes.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.lifecycleOwner = this
        binding.recyclerView.adapter = adapter

        setHasOptionsMenu(true)

        binding.floatingActionButton.setOnClickListener() {

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.byDate -> {

                viewModel.sort(1)

                true
            }
            R.id.byTitle -> {
                viewModel.sort(3)
                true
            }
            R.id.byColor -> {
                viewModel.sort( 5)
                true
            }
            R.id.byContent -> {
                viewModel.sort( 7)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


//        NavigationUI.
//        onNavDestinationSelected(item,requireView().findNavController())
//                || super.onOptionsItemSelected(item)
    }
}