package com.fahmisbas.notes.create

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fahmisbas.notes.R
import com.fahmisbas.notes.foundations.ApplicationScope
import com.fahmisbas.notes.foundations.NullFieldChecker
import com.fahmisbas.notes.models.Note
import com.fahmisbas.notes.notes.INoteModel
import kotlinx.android.synthetic.main.fragment_create_note.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import toothpick.Toothpick
import javax.inject.Inject


class CreateNoteFragment : Fragment(), NullFieldChecker {

    @Inject
    lateinit var model: INoteModel

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toothpick.inject(this, ApplicationScope.scope)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_note, container, false)
    }

    fun saveNote(callback: (Boolean) -> Unit) {
        GlobalScope.launch {
            createNote()?.let { note ->
                model.addNote(note) { success ->
                    callback.invoke(success)
                }
            } ?: callback.invoke(false)
        }
    }

    fun createNote(): Note? {
        return if (!hasNullField()) {
            Note(description = noteEditText.editableText.toString())
        } else null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        }
    }

    override fun hasNullField(): Boolean = noteEditText.editableText.isNullOrEmpty()

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction()
    }

    companion object {
        fun newInstance() = CreateNoteFragment()
    }
}
