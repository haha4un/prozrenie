package com.example.prozrenie

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.core.view.get
import com.example.drawingapp.DrawingView

import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.sql.Time
import java.time.LocalDateTime
import java.util.*
import kotlin.random.Random


class paint : AppCompatActivity() {

    private var mImageButtonCurrentPaint: ImageButton? = null
    var drawingView: DrawingView ?= null
    var imageViewBackground: ImageView ?= null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paint)
        supportActionBar?.hide()

        drawingView = findViewById<DrawingView>(R.id.drawingView)
        imageViewBackground = findViewById<ImageView>(R.id.imageViewBackground)
        var paintColorsLayout = findViewById<LinearLayout>(R.id.paintColorsLayout)
        var imageBrushSize = findViewById<SeekBar>(R.id.imageBrushButton)
        var drawingFrameLayout = findViewById<FrameLayout>(R.id.drawingFrameLayout)
        var imageSaveButton = findViewById<ImageButton>(R.id.imageSaveButton)
        var imageUndoButton = findViewById<ImageButton>(R.id.imageUndoButton)
        var imageGalleryButton = findViewById<ImageButton>(R.id.imageGalleryButton)

        imageViewBackground?.setBackgroundColor(Color.WHITE)

        mImageButtonCurrentPaint = paintColorsLayout[1] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.save))

        var x = 0f
        imageBrushSize.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                x = progress.toFloat()
                drawingView?.setSizeForBrush(x)
            }
        })

        var reset = findViewById<ImageButton>(R.id.new_btn)
        reset.setOnClickListener{
            for (x in 0..1000)//ahah, that's funny)
                drawingView?.onClickUndo()
        }

        imageGalleryButton.setOnClickListener {
            if(isReadStorageAllowed()){
                val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(pickPhotoIntent, GALLERY)

            }else{
                requestStoragePermission()
            }
        }

        imageUndoButton.setOnClickListener {
            drawingView?.onClickUndo()
        }

        imageSaveButton.setOnClickListener {
            if(isReadStorageAllowed()) {
                BitmapAsyncTask(getBitmapFromView(drawingFrameLayout), this).execute()
            }else {
                requestStoragePermission()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == GALLERY){
                try {
                    if(data!!.data != null){
                        imageViewBackground?.visibility = View.VISIBLE
                        imageViewBackground?.setImageURI(data.data)
                    }else{
                        Toast.makeText(this, "error in parsing the image", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

//    private fun showBrushSizeChooseDialog(){
//        val brushDialog = Dialog(this)
//        brushDialog.setContentView(R.layout.dialog_brush_size)
//        brushDialog.setTitle("Brush size: ")
//        val smallButton = brushDialog.smallBrushButton
//        smallButton.setOnClickListener {
//            drawingView.setSizeForBrush(10.toFloat())
//            brushDialog.dismiss()
//        }
//        val mediumButton = brushDialog.mediumBrushButton
//        mediumButton.setOnClickListener {
//            drawingView.setSizeForBrush(20.toFloat())
//            brushDialog.dismiss()
//        }
//        val largeButton = brushDialog.largeBrushButton
//        largeButton.setOnClickListener {
//            drawingView.setSizeForBrush(30.toFloat())
//            brushDialog.dismiss()
//        }
//
//        brushDialog.show()
//    }

    fun paintClicked(view: View) {
        if (view !== mImageButtonCurrentPaint) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()

            if (colorTag == "#ffffcc99")
                openColorPicker(imageButton)
            else
                drawingView?.setColor(colorTag)

            imageButton.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pallet_pressed))

            mImageButtonCurrentPaint!!.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.pallet_normal))

            mImageButtonCurrentPaint = view
        }
    }
    fun openColorPicker(i: ImageButton) {
        val colorPicker = AmbilWarnaDialog(this, R.color.skin, object : OnAmbilWarnaListener {
            override fun onCancel(dialog: AmbilWarnaDialog) {}
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onOk(dialog: AmbilWarnaDialog, color: Int) {
                i.setBackgroundColor(color)
                drawingView?.setColor(color)
            }
        })
        colorPicker.show()
    }

    private fun requestStoragePermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE).toString())){
            Toast.makeText(this, "Need permission to add background", Toast.LENGTH_SHORT).show()
        }
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE), STORAGE_PERMISSION_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Now can read storage", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "oops cant read storage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isReadStorageAllowed(): Boolean{
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val returnBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(returnBitmap)
        val bgDrawable = view.background
        if(bgDrawable != null){
            bgDrawable.draw(canvas)
        }else{
            canvas.drawColor(Color.WHITE)
        }

        view.draw(canvas)

        return returnBitmap
    }
    fun saveImage(finalBitmap: Bitmap, image_name: String) {
        val root = Environment.getExternalStorageDirectory().toString()
        val myDir = File("$root/Pictures")
        myDir.mkdirs()
        val fname = "Image-$image_name.jpg"
        val file = File(myDir, fname)
        if (file.exists()) file.delete()
        Toast.makeText(this, "load in $myDir", Toast.LENGTH_SHORT).show()
        try {
            val out = FileOutputStream(file)
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)
            out.flush()
            out.close()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
        }
    }

    private inner class BitmapAsyncTask(val mBitmap: Bitmap, val context: Context): AsyncTask<Any, Void, String>(){

        private lateinit var  mProgressDialog: Dialog

        override fun onPreExecute() {
            super.onPreExecute()
//            showProgressDialog()
        }

        override fun doInBackground(vararg params: Any?): String {
            var result = ""

            try {
                val bytes = ByteArrayOutputStream()
                val fileName = "DrawingApp_" + System.currentTimeMillis()/1000 + ".png"
                var fos : OutputStream? = null
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    context.contentResolver?.also { resolver ->
                        val contentValues = ContentValues().apply {
                            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                        }
                        val imageUri: Uri? =
                            resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                        fos = imageUri?.let { resolver.openOutputStream(it) }
                    }
                } else {
                    val imagesDir =
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    val image = File(imagesDir, fileName)
                    fos = FileOutputStream(image)
                }
                fos?.use {
                    mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
                }
                fos?.write(bytes.toByteArray())
                fos?.close()
                result = fileName

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            cancelProgressDialog()
            if (result!!.isNotEmpty()) {
                Toast.makeText(
                    this@paint,
                    "File saved successfully :$result",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this@paint,
                    "Something went wrong while saving the file.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            MediaScannerConnection.scanFile(this@paint, arrayOf(result), null){
                    path, uri -> val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
                shareIntent.type = "image/png"

                startActivity(Intent.createChooser(shareIntent, "Share"))
            }
        }

//        private fun showProgressDialog(){
//            mProgressDialog = Dialog(this@paint)
//            mProgressDialog.setContentView(R.layout.dialog_custom_progress)
//            mProgressDialog.show()
//        }

        private fun cancelProgressDialog(){
            mProgressDialog.dismiss()
        }

    }

    companion object{
        private const val STORAGE_PERMISSION_CODE = 1
        private const val GALLERY = 2
    }
}