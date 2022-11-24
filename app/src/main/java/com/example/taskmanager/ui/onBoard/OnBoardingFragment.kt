package com.example.taskmanager.ui.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.data.local.Pref
import com.example.taskmanager.databinding.FragmentOnBoardingBinding
import com.example.taskmanager.data.model.OnBoard
import com.example.taskmanager.ui.home.adapter.OnBoardingAdapter


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())

        val adapter = OnBoardingAdapter(this::onClick)
        adapter.add(
            OnBoard(
                "data:image/jpeg;base64,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",
                "Cat",
                "1"
            )
        )
        adapter.add(
            OnBoard(
                "https://img.lovepik.com/free-png/20211228/lovepik-male-express-png-image_400948036_wh300.png",
                "Dostavka",
                "2"
            )
        )
        adapter.add(
            OnBoard(

                "https://e1.pngegg.com/pngimages/255/162/png-clipart-render-naruto-uzumaki-naruti.png",
                "3",
                "Naruto"
            )
        )
        binding.viewPager.adapter = adapter
        binding.indicator.setViewPager(binding.viewPager)

    }

    private fun onClick(view: View) {
        findNavController().navigateUp()
        pref.saveShowBoarding(true)

    }
}