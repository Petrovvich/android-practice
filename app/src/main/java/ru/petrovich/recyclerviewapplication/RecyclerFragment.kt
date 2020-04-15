package ru.petrovich.recyclerviewapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R
import ru.petrovich.recyclerviewapplication.mock.MockAdapter
import ru.petrovich.recyclerviewapplication.mock.MockGenerator

class RecyclerFragment : Fragment() {
    private lateinit var mRecycler: RecyclerView
    private val mockAdapter = MockAdapter()

    companion object NewInstance {
        fun newInstance(): RecyclerFragment {
            return RecyclerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mRecycler = view.findViewById(R.id.recycler)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mRecycler.layoutManager = LinearLayoutManager(activity)
        mRecycler.adapter = mockAdapter
        mockAdapter.addData(MockGenerator.generate(56))
    }
}