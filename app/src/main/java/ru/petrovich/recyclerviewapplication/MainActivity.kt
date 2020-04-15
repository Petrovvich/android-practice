package ru.petrovich.recyclerviewapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R

class MainActivity : AppCompatActivity() {

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
}
