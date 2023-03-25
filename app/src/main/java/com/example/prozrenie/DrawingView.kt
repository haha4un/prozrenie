package com.example.drawingapp

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import com.example.prozrenie.paint
import com.example.prozrenie.paint.Companion.brushs
import com.example.prozrenie.paint.Companion.path

class DrawingView(context: Context, attr: AttributeSet): View(context, attr) {

    var params: ViewGroup.LayoutParams? =null
    companion object
    {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
        var currentBrush = Color.GREEN
    }

//    // declaration of variables
//    private var mDrawPath: CustomPath? = null
//    private var mCanvasBitmap: Bitmap? = null
//    private var mDrawPaint: Paint? = null
//    private var mCanvasPaint: Paint? = null
//    private var mBrushSize: Float = 10.0F
//    private var color = Color.GREEN
//    private var canvas: Canvas? = null


//    fun edit(c: String, s: Float)
//    {
//        this.color = Color.parseColor(c)
//        this.mBrushSize = s
//    }
//    fun DrawingView(Path: Path, Paint: Paint, c: String, s: Float)
//    {
//        mDrawPath = CustomPath(Color.parseColor(c),s)
//        mDrawPaint = Paint
//    }

//    fun reset()
//    {
//        mDrawPath!!.reset()
//    }
    init{
        brushs.isAntiAlias = true
        brushs.color = currentBrush
        brushs.style = Paint.Style.STROKE
        brushs.strokeJoin = Paint.Join.ROUND
        brushs.strokeWidth = 10f

        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//        setUpDrawing()
    }

//    private fun setUpDrawing(){
//        mDrawPaint = Paint()
//        mDrawPath = CustomPath(color, mBrushSize)
//        mDrawPaint!!.color = color
//        mDrawPaint!!.style = Paint.Style.STROKE
//        mDrawPaint!!.strokeJoin = Paint.Join.ROUND
//        mDrawPaint!!.strokeCap = Paint.Cap.ROUND
//        mCanvasPaint = Paint(Paint.DITHER_FLAG)
//        mBrushSize = 20f
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

//        mCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
//        canvas = Canvas(mCanvasBitmap!!)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in pathList.indices)
        {
            brushs.color = colorList[i]
            canvas.drawPath(pathList[i], brushs)
            invalidate()
        }
//        canvas.drawBitmap(mCanvasBitmap!!, 0.0F, 0.0F, mCanvasPaint)
//        if(!mDrawPath!!.isEmpty) {
//            mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness
//            mDrawPaint!!.color = mDrawPath!!.color
//            canvas.drawPath(mDrawPath!!, mDrawPaint!!)
//        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
//                path.color = color
//                path.brushThickness = mBrushSize
//                mDrawPath!!.reset()
                path.moveTo(x!!, y!!)
            }
            MotionEvent.ACTION_MOVE -> {
//                path.lineTo(touchX!!, touchY!!)
                path.lineTo(x!!, y!!)
                pathList.add(path)
                colorList.add(currentBrush)
            }
            else -> return false
        }
        postInvalidate()
        return true
    }


    internal inner class CustomPath(var color: Int, var brushThickness: Float): Path(){
    }

}