package com.adilhon.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.adilhon.noteapp.R
import com.adilhon.noteapp.databinding.FragmentOnBoardPagingBinding

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
    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0->{
                onTxt.text = "Очень удобный функционал"
                lottieAnimation.setAnimation(R.raw.first)
            }
            1->{
                onTxt.text = "Быстрый, качественный продукт"
                lottieAnimation.setAnimation(R.raw.second )
            }
            2->{
                onTxt.text = "Куча функций и интересных фишек"
                start.isVisible = true
                lottieAnimation.setAnimation(R.raw.thrid)
            }
        }
    }

    companion object{
        const val ARG_ONBOARD_POSITION = "onBoard"
    }
}