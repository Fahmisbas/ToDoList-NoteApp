package com.fahmisbas.notes.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fahmisbas.notes.R
import com.fahmisbas.notes.models.Note
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = NoteAdapter(
            mutableListOf(
                Note("Test 1"),
                Note("Test 2"),
                Note("Test 3"),
                Note("Test 4")
            )
        )
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = NotesListFragment()
    }
}
