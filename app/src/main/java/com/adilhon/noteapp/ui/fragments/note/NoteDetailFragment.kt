package com.adilhon.noteapp.ui.fragments.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.adilhon.noteapp.App
import com.adilhon.noteapp.R
import com.adilhon.noteapp.databinding.FragmentNoteDetailBinding
import com.adilhon.noteapp.ui.fragments.models.NoteModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupListeners()
    }

    private fun setupView() {
    }

    private fun setupListeners() = with(binding) {
        tvDone.setOnClickListener {
            App.noteDatabase.noteDao().insertNote(
                NoteModel(
                    title = etTitle.text.toString(),
                    description = etDescription.text.toString(),
                    time = getCurrentTime(),
                    containerColor = getCurrentColor()
                )
            )
            findNavController().navigate(R.id.action_noteDetailFragment_to_noteFragment2)
        }
    }

    fun getCurrentTime(): String {
        val currentTimeMillis = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
        val date = Date(currentTimeMillis)
        return dateFormat.format(date)
    }

    private fun getCurrentColor(): String {
        return if (binding.rb1.isChecked) {
            "gray"
        } else if (binding.rb2.isChecked) {
            "white"
        } else if (binding.rb3.isChecked) {
            "red"
        }else "gray"
    }
}