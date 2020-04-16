package ru.petrovich.recyclerviewapplication

import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ru.petrovich.list.view.yandex.map.recyclerviewapplication.R
import ru.petrovich.recyclerviewapplication.listeners.OnItemClickListener

class RecyclerFragment : Fragment(),
    SwipeRefreshLayout.OnRefreshListener,
    LoaderManager.LoaderCallbacks<Cursor> {
    private lateinit var mRecycler: RecyclerView
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mErrorView: View
    private val contactsAdapter = ContactsAdapter()
    private var listener: OnItemClickListener? = null

    companion object NewInstance {
        fun newInstance(): RecyclerFragment {
            return RecyclerFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListener) listener = context
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
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
        mRecycler.adapter = contactsAdapter
        contactsAdapter.listener = listener
    }

    override fun onRefresh() {
        loaderManager.restartLoader(0, null, this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(
            activity as Context,
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME),
            null,
            null,
            ContactsContract.Contacts._ID
        )
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        contactsAdapter.swapCursor(data)
        if (mSwipeRefreshLayout.isRefreshing) mSwipeRefreshLayout.isRefreshing = false
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        TODO("Not yet implemented")
    }
}