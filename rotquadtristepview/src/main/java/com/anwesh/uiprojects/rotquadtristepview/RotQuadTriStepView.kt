package com.anwesh.uiprojects.rotquadtristepview

/**
 * Created by anweshmishra on 28/11/18.
 */

import android.view.View
import android.view.MotionEvent
import android.app.Activity
import android.content.Context
import android.graphics.*

val nodes : Int = 5
val tris : Int = 4
val scGap : Float = 0.05f
val scDiv : Double = 0.51
val color : Int = Color.parseColor("#0D47A1")
val sizeFactor : Float = 2.5f
val strokeFactor : Int = 120

fun Int.getInverse() : Float = 1f / this

fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.getInverse(), Math.max(0f, this - i * n.getInverse())) * n

fun Float.scaleFactor() : Float = Math.floor(this / scDiv).toFloat()

fun Float.mirrorValue(a : Int, b : Int) : Float = (1 - scaleFactor()) * a.getInverse() + scaleFactor() * b.getInverse()

fun Float.updateScale(dir : Int, a : Int, b : Int) : Float = mirrorValue(a, b) * scGap * dir

fun Canvas.drawRQTSNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    val gap : Float = w / (nodes + 1)
    val sc1 : Float = scale.divideScale(0, 2)
    val sc2 : Float = scale.divideScale(1, 2)
    val size : Float = gap / sizeFactor
    val ws : Float = size / 5
    val hs : Float = size
    paint.strokeWidth = Math.min(w, h) / strokeFactor
    paint.strokeCap = Paint.Cap.ROUND
    paint.color = color
    save()
    translate(gap * (i + 1), h/2)
    paint.style = Paint.Style.STROKE
    drawRect(RectF(-size, -size, size, size), paint)
    rotate(90f * sc2)
    for (j in 0..(tris - 1)) {
        val sc : Float = sc1.divideScale(j, tris)
        paint.style = Paint.Style.FILL
        save()
        rotate(90f * j)
        val path : Path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(-ws * sc, -hs * sc)
        path.lineTo(ws * sc, -hs * sc)
        drawPath(path, paint)
        restore()
    }
    restore()
}

class RotQuadTriStepView(ctx : Context) : View(ctx) {

    private val paint : Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += 0.05f * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}