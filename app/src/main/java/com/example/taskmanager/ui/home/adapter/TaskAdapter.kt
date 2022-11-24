package com.example.taskmanager.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taskmanager.data.model.Task
import com.example.taskmanager.databinding.ItemTaskBinding

class TaskAdapter() :
    RecyclerView.Adapter<TaskAdapter.TasViewHolder>() {

    var onClickListener: ((Int) -> Unit)? = null

    var onLongClickListener: ((Task) -> Unit)? = null

    private val tasks: ArrayList<Task> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasViewHolder {
        return TasViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: TasViewHolder, position: Int) {
        holder.bind(tasks.get(position))
    }

    fun addTask(task: Task) {
        tasks.add(0, task)
        notifyItemChanged(0)
    }

    fun addTask(newTasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(newTasks)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    //метод для удаления модельки адаптера
    fun delete(task: Task) {
        tasks.remove(task)
        notifyDataSetChanged()
    }


    inner class TasViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTitle.text = task.title
            binding.tvDeck.text = task.desk

            //иницализируем наш лонк клик
            itemView.setOnLongClickListener {
                onLongClickListener?.invoke(task)
                return@setOnLongClickListener true
            }
        }
    }


}