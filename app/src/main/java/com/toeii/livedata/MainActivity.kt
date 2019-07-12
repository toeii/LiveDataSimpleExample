package com.toeii.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var mLiveData: MutableLiveData<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mLiveData = MutableLiveData()

        mLiveData.observe(this, Observer<String> {
            updateText ->
            System.out.println("update new text ---> " + updateText)
        })

    }

    override fun onStart() {
        super.onStart()
        mLiveData.value = "start text from MainThread"
    }

    override fun onResume() {
        super.onResume()
        Thread(Runnable {
            mLiveData.postValue("resume text from BranchThread")
        }).run()
    }

}
