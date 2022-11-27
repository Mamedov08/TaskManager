package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskmanager.databinding.ItemOnBoardingBinding
import com.example.taskmanager.data.model.OnBoard

class OnBoardingAdapter(val onClick: (view: View) -> Unit) :
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val arrayList = arrayListOf<OnBoard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnBoardingBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {

        holder.bind(arrayList[position])


    }

    override fun getItemCount(): Int = arrayList.size
    fun add(onBoard: OnBoard) {
        arrayList.add(onBoard)
    }


    inner class OnBoardingViewHolder(private val binding: ItemOnBoardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onBoard: OnBoard) {
            binding.btnStar.isVisible = adapterPosition == arrayList.lastIndex
            binding.skip.isVisible = adapterPosition != arrayList.lastIndex
            binding.tvTitle.text = onBoard.title
            binding.tvDesk.text = onBoard.desc
            onBoard.image?.let { binding.image.setAnimation(it) }
            binding.btnStar.setOnClickListener {
                onClick(binding.btnStar)
            }
            binding.skip.setOnClickListener {
                onClick(binding.skip)
            }
        }

    }
}