package com.fahmisbas.notes.notes

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmisbas.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.view.*

class NoteListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var adapter: NoteAdapter
    private lateinit var touchActionDelegate : NotesListFragment.TouchActionDelegate
    private lateinit var dataActionDelegate : NoteListViewContract

    fun initViews(taDelegate : NotesListFragment.TouchActionDelegate,daDelegate : NoteListViewContract) {
        setUpDelegate(taDelegate,daDelegate)
        setupView()
    }

    private fun setUpDelegate(taDelegate : NotesListFragment.TouchActionDelegate, daDelegate: NoteListViewContract) {
        touchActionDelegate = taDelegate
        dataActionDelegate = daDelegate
    }

    private fun setupView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = NoteAdapter(touchActionDelegate =  touchActionDelegate,dataActionDelegate = dataActionDelegate)
        recyclerView.adapter = adapter
    }

    fun updateList(list : List<Note>) {
        adapter.updateList(list)
    }

}