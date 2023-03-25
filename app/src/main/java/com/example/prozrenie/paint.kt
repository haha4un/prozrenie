package com.example.prozrenie

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.AttributeSet
import android.util.Xml
import androidx.appcompat.app.AppCompatActivity
import com.example.drawingapp.DrawingView
import org.xmlpull.v1.XmlPullParser


class paint : AppCompatActivity() {
    @SuppressLint("MissingInflatedId", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)

        val parser: XmlPullParser = resources.getXml(R.layout.activity_paint)
        val attributes: AttributeSet = Xml.asAttributeSet(parser)

        var d = findViewById<DrawingView>(R.id.view)
        var dv: DrawingView = DrawingView(this, attributes)
        dv.edit("#0062ff", 10.3F)

    }
}