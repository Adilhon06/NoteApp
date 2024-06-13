package com.adilhon.noteapp.ui.fragments.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.viewpager2.widget.ViewPager2
import com.adilhon.noteapp.databinding.FragmentOnBoardBinding
import com.adilhon.noteapp.ui.adapters.OnBoardViewPagerAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class OnBoardFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
    }

    private fun initialize() {
        binding.viewPager2.adapter = OnBoardViewPagerAdapter(this)
        val indicator: DotsIndicator = binding.dotsIndicator
        indicator.attachTo(binding.viewPager2)
    }

    private fun setupListeners() = with(binding.viewPager2) {
        binding.txtSkip.setOnClickListener {
            if (currentItem < 2) {
                setCurrentItem(currentItem + 2, true)

            }
            if (currentItem < 1) {
                setCurrentItem(currentItem + 1, true)
            }
        }

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.txtSkip.isInvisible = currentItem == 2
            }
        })
    }
}