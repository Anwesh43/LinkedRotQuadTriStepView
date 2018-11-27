package com.anwesh.uiprojects.rotquadtristepview

/**
 * Created by anweshmishra on 28/11/18.
 */

import android.view.View
import android.view.MotionEvent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.Path
import android.app.Activity
import android.content.Context

val nodes : Int = 5
val tris : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val color : Int = Color.parseColor("#0D47A1")
val sizeFactor : Float = 2.5f
val strokeFactor : Int = 120
