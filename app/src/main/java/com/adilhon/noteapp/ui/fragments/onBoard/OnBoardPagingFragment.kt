package com.adilhon.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.adilhon.noteapp.R
import com.adilhon.noteapp.databinding.FragmentOnBoardPagingBinding
import com.adilhon.noteapp.utils.PreferenceHelper

class OnBoardPagingFragment : Fragment() {

    private lateinit var binding: FragmentOnBoardPagingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        goToNoteFragment()
    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0->{
                tvMeeting.text = "Очень удобный функционал"
                lottieAnimation.setAnimation(R.raw.first)
            }
            1->{
                tvMeeting.text = "Быстрый, качественный продукт"
                lottieAnimation.setAnimation(R.raw.second )
            }
            2->{
                tvMeeting.text = "Куча функций и интересных фишек"
                tvStart.isVisible = true
                lottieAnimation.setAnimation(R.raw.thrid)
            }
        }
    }

    private fun goToNoteFragment() = with(binding) {
        val preferenceHelper = PreferenceHelper()
        preferenceHelper.unit(requireContext())
        tvStart.setOnClickListener {
            preferenceHelper.saveBoolean = true
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}