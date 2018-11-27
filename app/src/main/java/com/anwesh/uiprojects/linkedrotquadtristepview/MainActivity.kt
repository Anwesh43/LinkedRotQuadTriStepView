package com.anwesh.uiprojects.linkedrotquadtristepview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anwesh.uiprojects.rotquadtristepview.RotQuadTriStepView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RotQuadTriStepView.create(this)
    }
}
