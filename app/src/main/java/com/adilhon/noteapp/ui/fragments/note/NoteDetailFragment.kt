package com.adilhon.noteapp.ui.fragments.note

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
    private var model: NoteModel? = null
    private var newModel: NoteModel? = null
    private var isFirstText: Boolean = false
    private var isSecondText: Boolean = false

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

        binding.etTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isFirstText = s != null
                if (isFirstText && isSecondText) {
                    binding.tvDone.visibility = View.VISIBLE
                } else binding.tvDone.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        binding.etDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                isSecondText = s != null
                if (isFirstText && isSecondText) {
                    binding.tvDone.visibility = View.VISIBLE
                } else binding.tvDone.visibility = View.GONE
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun setupView() {
        if (arguments != null) {
            model = arguments?.getSerializable("model") as NoteModel
            if (model != null) {
                binding.etTitle.setText(model!!.title)
                binding.etDescription.setText(model!!.description)
                binding.rb1.isChecked = model!!.containerColor == "gray"
                binding.rb2.isChecked = model!!.containerColor == "white"
                binding.rb3.isChecked = model!!.containerColor == "red"
            }
        }
    }

    private fun setupListeners() = with(binding) {
        tvDone.setOnClickListener {
            if (rb1.isChecked || rb2.isChecked || rb3.isChecked) {
                if (arguments != null) {
                    App.noteDatabase.noteDao().updateNote(
                        NoteModel(
                            id = model!!.id,
                            title = etTitle.text.toString(),
                            description = etDescription.text.toString(),
                            time = getCurrentTime(),
                            containerColor = getCurrentColor()
                        )
                    )
                    findNavController().navigate(R.id.action_noteDetailFragment_to_noteFragment2)
                } else {
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
            }else {
                Toast.makeText(requireContext(), "Выберите цвет картошки", Toast.LENGTH_SHORT).show()
            }
        }
        ivArrow.setOnClickListener {
            findNavController().navigateUp()
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
        } else "gray"
    }
}