package com.example.el_work

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.el_work.DataBase.DatabaseHelper
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.view.MotionEvent
import android.view.View
class ClassifyActivity2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val colors = mutableListOf<Int>()
    private lateinit var imageView: ImageView
    private lateinit var canvas: Canvas
    private lateinit var paint:Paint
    private lateinit var bitmap: Bitmap
    private lateinit var dbHelper: DatabaseHelper

    private var startX = 0f
    private var startY = 0f


    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity2)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dbHelper = DatabaseHelper(this)
        val saveButton: Button = findViewById(R.id.button9)
        saveButton.setOnClickListener{
            saveBitmap()
        }

        val button1 = findViewById<Button>(R.id.button6)
        val button2 = findViewById<Button>(R.id.button7)
        val button3 = findViewById<Button>(R.id.button8)
        val button4 = findViewById<Button>(R.id.button10)
        val button5 = findViewById<Button>(R.id.button)
        var initialButtonColor = button4.backgroundTintList?.defaultColor ?: Color.BLACK

        imageView = findViewById(R.id.imageView5)
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.hehuatest)
        bitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, null)
        imageView.setImageBitmap(bitmap)

        imageView.setOnTouchListener { v, event ->
            val scaledX = event.x / v.width * bitmap.width
            val scaledY = event.y / v.height * bitmap.height

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = scaledX
                    startY = scaledY
                }
                MotionEvent.ACTION_MOVE -> {
                    canvas.drawLine(startX, startY, scaledX, scaledY, paint)
                    imageView.invalidate()
                    startX = scaledX
                    startY = scaledY
                }
            }
            true
        }
        paint = Paint().apply {
            color = initialButtonColor
            strokeWidth = 45f
            style = Paint.Style.STROKE
        }

        button1.setOnClickListener {
            val defaultColor = Color.parseColor("#FFFFFFFF")
            val currentColor = button1.backgroundTintList?.getColorForState(button1.drawableState, defaultColor)
            if (currentColor != null) {
                colors.add(currentColor)
            }
            paint.color=Color.rgb(255,0,0)
            button1.invalidate()

        }

        button2.setOnClickListener {
            val defaultColor = Color.parseColor("#FFFFFFFF")
            val currentColor = button2.backgroundTintList?.getColorForState(button2.drawableState, defaultColor)
            if (currentColor != null) {
                colors.add(currentColor)
            }
            paint.color=Color.rgb(0,255,0)
            button2.invalidate()
        }

        button3.setOnClickListener {
            val defaultColor = Color.parseColor("#FFFFFFFF")
            val currentColor = button3.backgroundTintList?.getColorForState(button3.drawableState, defaultColor)
            if (currentColor != null) {
                colors.add(currentColor)
            }

            paint.color=Color.rgb(0,0,255)
            button3.invalidate()
        }
        button5.setOnClickListener {
            val defaultColor = Color.parseColor("#FFFFFFFF")
            val currentColor = button3.backgroundTintList?.getColorForState(button3.drawableState, defaultColor)
            if (currentColor != null) {
                colors.add(currentColor)
            }

            paint.color=Color.rgb(85,85,85)
            button5.invalidate()
        }
    }

    private fun saveBitmap() {
        val imageView: ImageView = findViewById(R.id.imageView5)
        val context: Context = imageView.context
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.qiwu)
        val savedImagePath = saveBitmapToStorage(bitmap)
        if (savedImagePath != null) {
            saveImagePathToDate(savedImagePath)
            Toast.makeText(this, "Drawing saved to $savedImagePath", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "Failed to save drawing", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveBitmapToStorage(bitmap: Bitmap): String? {
        val context = applicationContext
        val fileDir = File(context.filesDir, "images")
        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }
        val fileName = "drawing_${System.currentTimeMillis()}.png"
        val imageFile = File(fileDir, fileName)
        try {
            val fos = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.close()
            return imageFile.absolutePath
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
    }

    private fun saveImagePathToDate(path: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("image_path", path)
        }
        db.insert("drawings", null, values)
        println(path)
    }

    fun goToActivity2(view: View) {
        val intent = Intent(this, ClassifyActivity2::class.java)
        startActivity(intent)
    }

}