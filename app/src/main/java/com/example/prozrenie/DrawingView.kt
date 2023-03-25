package com.example.drawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.prozrenie.paint.Companion.brushs
import com.example.prozrenie.paint.Companion.path
import com.example.prozrenie.paint.Companion.s


class DrawingView(context: Context, attr: AttributeSet): View(context, attr) {

    var params: ViewGroup.LayoutParams? =null
    companion object
    {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var sizeList = ArrayList<Float>()
        var currentBrush = Color.GREEN
    }
    init{
        brushs.isAntiAlias = true
        brushs.color = currentBrush
        brushs.style = Paint.Style.STROKE
        brushs.strokeJoin = Paint.Join.ROUND
        brushs.strokeWidth = s
        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun setStrokeWidth(strokeWidth: Float) {
        brushs.strokeWidth = strokeWidth
        postInvalidate()
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in pathList.indices)
        {
            brushs.color = colorList[i]
            brushs.strokeWidth = sizeList[i]
            canvas.drawPath(pathList[i], brushs)
            invalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x!!, y!!)
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x!!, y!!)
                pathList.add(path)
                colorList.add(currentBrush)
                sizeList.add(s)
            }
            else -> return false
        }
        postInvalidate()
        return true
    }
    internal inner class CustomPath(var color: Int, var brushThickness: Float): Path(){
    }
//    fun save(): Bitmap? {
//        val b = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
//        val c = canvas
//        layout(left, top, right, bottom)
//        draw(c)
//        return b
//    }
//    fun clearDrawing() {
//        path?.reset() // Avoiding saving redo from Path().
//        pathList.clear()
//        colorList.clear()
//        sizeList.clear()
//        invalidate()
//    }
}