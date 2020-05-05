package ru.petrovich.recyclerviewapplication.mock

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R
import ru.petrovich.recyclerviewapplication.listeners.OnItemClickListener

class MockHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val mName: TextView = view.findViewById(R.id.tv_name)
    private val mValue: TextView = view.findViewById(R.id.tv_value)
    private lateinit var mId: String

    fun bind(mock: Mock) {
        mName.text = mock.name
        mValue.text = mock.value
        mId = mock.value
    }

    fun listener(listener: OnItemClickListener?) {
        itemView.setOnClickListener {
            listener?.onItemClick(mId)
        }
    }
}