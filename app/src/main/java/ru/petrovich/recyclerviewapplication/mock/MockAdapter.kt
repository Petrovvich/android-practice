package ru.petrovich.recyclerviewapplication.mock

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R

class MockAdapter : RecyclerView.Adapter<MockHolder>() {

    private val mockList = ArrayList<Mock>()

    override fun onBindViewHolder(holder: MockHolder, position: Int) {
        holder.bind(mockList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockHolder {
        return MockHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_mock, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mockList.size
    }

    fun addData(mocks: List<Mock>) {
        mockList.addAll(mocks)
        notifyDataSetChanged()
    }
}