package ru.petrovich.recyclerviewapplication.mock

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R

class MockHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mName = view.findViewById<TextView>(R.id.tv_name)
    private val mValue = view.findViewById<TextView>(R.id.tv_value)

    fun bind(mock: Mock) {
        mName.text = mock.name
        mValue.text = mock.value
    }
}