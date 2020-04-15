package ru.petrovich.recyclerviewapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R
import ru.petrovich.recyclerviewapplication.mock.MockAdapter
import ru.petrovich.recyclerviewapplication.mock.MockGenerator
import java.util.*

class RecyclerFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mErrorView: View
    private val mockAdapter = MockAdapter()
    private val mRandom = Random()

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
        mSwipeRefreshLayout = view.findViewById(R.id.refresher)
        mSwipeRefreshLayout.setOnRefreshListener(this)
        mErrorView = view.findViewById(R.id.error_view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mRecycler.layoutManager = LinearLayoutManager(activity)
        mRecycler.adapter = mockAdapter
    }

    override fun onRefresh() {
        mSwipeRefreshLayout.postDelayed(
            {
                var count = mRandom.nextInt(1)

                if (count == 0) {
                    if (mSwipeRefreshLayout.isRefreshing) mSwipeRefreshLayout.isRefreshing = false
                    mErrorView.visibility = View.VISIBLE
                    mRecycler.visibility = View.GONE
                } else {
                    if (mSwipeRefreshLayout.isRefreshing) mSwipeRefreshLayout.isRefreshing = false
                    mErrorView.visibility = View.GONE
                    mRecycler.visibility = View.VISIBLE
                    mockAdapter.addData(MockGenerator.generate(count), false)
                }

            },
            2000
        )
    }
}