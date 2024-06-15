package com.adilhon.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adilhon.noteapp.R
import com.adilhon.noteapp.databinding.FragmentNoteBinding
import com.adilhon.noteapp.ui.adapters.NoteAdapter
import com.adilhon.noteapp.ui.fragments.extension.getBackStackData
import com.adilhon.noteapp.ui.fragments.models.NoteModel

class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter
    private val list: ArrayList<NoteModel> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
     }

    private fun initialize() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupListeners() = with(binding) {
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
    }
}