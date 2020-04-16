package ru.petrovich.recyclerviewapplication

import android.database.Cursor
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R
import ru.petrovich.recyclerviewapplication.mock.Mock
import ru.petrovich.recyclerviewapplication.mock.MockHolder

class ContactsAdapter : RecyclerView.Adapter<MockHolder>() {

    private var mCursor: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MockHolder {
        return MockHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.list_item_mock, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mCursor?.count ?: 0
    }

    override fun onBindViewHolder(holder: MockHolder, position: Int) {
        if (mCursor?.moveToPosition(position)!!) {
            val name = mCursor!!
                .getString(
                    mCursor!!
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                )
            val id = mCursor!!
                .getInt(
                    mCursor!!
                        .getColumnIndex(ContactsContract.Contacts._ID)
                )
            holder.bind(Mock(name, id))
        }
    }

    fun swapCursor(cursor: Cursor?) {
        if (cursor != null && cursor != mCursor) {
            mCursor?.close()
            mCursor = cursor
            notifyDataSetChanged()
        }
    }
}