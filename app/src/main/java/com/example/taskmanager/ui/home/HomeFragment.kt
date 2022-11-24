package com.example.taskmanager.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taskmanager.App
import com.example.taskmanager.R
import com.example.taskmanager.databinding.FragmentHomeBinding
import com.example.taskmanager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {
    lateinit var adapter: TaskAdapter

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var data = App.db.taskDao().getAllTask()
        adapter.addTask(data)

        binding.recyclerTask.adapter = adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)

        }
        //используем нам созданный лонк клик
        adapter.onLongClickListener = { task ->
            //создали алерт дайлок для удаления да/нет
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Вы действительно хотите удалить?")
            //негативная кнопка нет
            alertDialog.setNegativeButton(
                "нет",
                DialogInterface.OnClickListener { dialogInterface, i ->

                })
            //позитивная кнопка да
            alertDialog.setPositiveButton(
                "да",
                DialogInterface.OnClickListener { dialogInterface, i ->
                    //внутри позитивной кнопки "да" мы удаляем нашу модельку внутри руума
                    App.db.taskDao().delete(task)
                    //тут мы удаляем уже внутри адаптера
                    adapter.delete(task)

                })
            alertDialog.create().show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}