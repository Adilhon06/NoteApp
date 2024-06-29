package com.adilhon.noteapp.ui.fragments.note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adilhon.noteapp.App
import com.adilhon.noteapp.R
import com.adilhon.noteapp.databinding.FragmentNoteBinding
import com.adilhon.noteapp.ui.adapters.NoteAdapter
import com.adilhon.noteapp.ui.fragments.models.NoteModel


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private val noteAdapter: NoteAdapter by lazy {
        NoteAdapter(requireContext(), ::onClick)
    }
    private val noteList: ArrayList<NoteModel> = ArrayList()

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
        setupViews()
        setupListeners()
    }

    private fun initialize() {
        binding.rvNotes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = noteAdapter
        }
    }

    private fun setupViews() {
        val getList = App.noteDatabase.noteDao().getAllNotes()
        if (getList.isNotEmpty()) {
            noteAdapter.submitList(getList)
            noteAdapter.notifyDataSetChanged()
        }
    }

    private fun setupListeners() = with(binding) {
        btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment)
        }
        var isList = false
        ivGrit.setOnClickListener {
            if (!isList) {
                binding.rvNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.ivGrit.setImageResource(R.drawable.ic_linear_menu)
                isList = true
            }else if (isList){
                binding.ivGrit.setImageResource(R.drawable.ic_grid_layout)
                isList = false
                binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
            }
        }
    }

    private fun onClick(item: NoteModel) {
        val bundle = Bundle()
        bundle.putSerializable("model", item)
        findNavController().navigate(R.id.action_noteFragment_to_noteDetailFragment, bundle)
    }
}
