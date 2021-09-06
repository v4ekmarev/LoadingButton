package com.example.loadingbutton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myapplication.LoadingButton
import com.example.myapplication.LoadingButtonState

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val btnLoading = findViewById<LoadingButton>(R.id.btnLoading)

        val btn = findViewById<Button>(R.id.btn1)

        btn.setOnClickListener {
            if (btnLoading.getState() == LoadingButtonState.LOADING) {
                btnLoading.updateState(LoadingButtonState.COMPLETE)
            } else {
                btnLoading.updateState(LoadingButtonState.LOADING)
            }
        }
    }
}