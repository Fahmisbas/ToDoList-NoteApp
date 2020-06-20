package com.fahmisbas.notes.create

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fahmisbas.notes.R
import com.fahmisbas.notes.navigation.NavigationActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        intent.getStringExtra(NavigationActivity.FRAGMENT_TYPE_KEY).run {
            textView.text = if (this ==
                NavigationActivity.FRAGMENT_VALUE_TASK) {
                "This is From Fragment Task"
            }else if (this == NavigationActivity.FRAGMENT_VALUE_NOTE) {
                "This is From Fragment Note"
            }else {
                "Something went wrong"
            }
        }
    }
}
