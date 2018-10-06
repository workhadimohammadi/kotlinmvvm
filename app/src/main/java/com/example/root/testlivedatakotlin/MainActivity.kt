package com.example.root.testlivedatakotlin

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mModel: NameViewModel

    private lateinit var  txtResult:TextView
    private lateinit var  btnRun:Button
    private lateinit var  edtEntry:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        setLiveData()
        setViews()
    }

    fun initialize(){
        txtResult=findViewById(R.id.text_result)
        edtEntry=findViewById(R.id.entry)
        btnRun=findViewById(R.id.run)
    }
    fun setLiveData(){
        mModel = ViewModelProviders.of(this).get(NameViewModel::class.java)


        // Create the observer which updates the UI.
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            txtResult.text=newName
        }

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        mModel.currentName.observe(this, nameObserver)
    }

    fun setViews(){
        btnRun.setOnClickListener {
            var entry: String= edtEntry.text.toString()
            mModel.currentName.setValue(entry)
        }
    }
}
