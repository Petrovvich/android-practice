package ru.petrovich.recyclerviewapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R
import ru.petrovich.recyclerviewapplication.listeners.OnItemClickListener

class MainActivity : AppCompatActivity(), OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, RecyclerFragment.newInstance())
                .commit()
        }
    }

    override fun onItemClick(id: String) {
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND "
                    + ContactsContract.CommonDataKinds.Phone.TYPE + " = ?",
            arrayOf(id, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE.toString()),
            null
        )
        if (cursor != null && cursor.moveToFirst()) {
            val number =
                cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            cursor.close()
            startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:".plus(number))))
        }
        Toast.makeText(this, "Clicked $id", Toast.LENGTH_LONG).show()
    }
}
