package com.example.el_work

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.el_work.DataBase.DatabaseHelper
import android.os.Bundle
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
import android.graphics.PorterDuffXfermode
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
    var isErasing = false

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
        val saveButton: Button = findViewById(R.id.button_save)
        saveButton.setOnClickListener{
            saveBitmap()
        }

        val button1 = findViewById<Button>(R.id.button_red)
        val button2 = findViewById<Button>(R.id.button_green)
        val button3 = findViewById<Button>(R.id.button_blue)
        val button4 = findViewById<Button>(R.id.button_yellow)
        val button5 = findViewById<Button>(R.id.button_black)
        val button6 = findViewById<Button>(R.id.button_5)
        val button7 = findViewById<Button>(R.id.button_2)
        val button8 = findViewById<Button>(R.id.button_4)
        val button9 = findViewById<Button>(R.id.button_1)
        val button10 = findViewById<Button>(R.id.button_purple)
        val button11 = findViewById<Button>(R.id.button_pink)
        val button12 = findViewById<Button>(R.id.button_orange)
        val buttonwider = findViewById<Button>(R.id.button_wider)
        val buttonnarrow= findViewById<Button>(R.id.button_narrow)
        val buttondeep= findViewById<Button>(R.id.button_deep)
        val buttonlight = findViewById<Button>(R.id.button_light)
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
                    if (isErasing) {
                        val clearPaint = Paint().apply {
                            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                            isAntiAlias = true
                        }
                        canvas.drawCircle(scaledX, scaledY, 25f, clearPaint)
                        imageView.invalidate()
                    } else {
                        canvas.drawLine(startX, startY, scaledX, scaledY, paint)
                        startX = scaledX
                        startY = scaledY
                        imageView.invalidate()
                    }
                }
            }
            true
        }
        paint = Paint().apply {
            color = initialButtonColor
            strokeWidth = 50f
            style = Paint.Style.STROKE
            alpha=128
        }

        button1.setOnClickListener {

            paint.color=Color.rgb(233,30,99)
            button1.invalidate()

        }

        button2.setOnClickListener {

            paint.color=Color.rgb(76,175,80)
            button2.invalidate()
        }

        button3.setOnClickListener {

            paint.color=Color.rgb(33,150,243)
            button3.invalidate()
        }
        button4.setOnClickListener {

            paint.color=Color.rgb(255,255,15)
            button4.invalidate()
        }
        button5.setOnClickListener {

            paint.color=Color.rgb(0,0,0)
            button5.invalidate()
        }
        button6.setOnClickListener {

            paint.color=Color.rgb(135,191,186)
            button6.invalidate()
        }
        button7.setOnClickListener {


            paint.color=Color.rgb(63,81,181)
            button7.invalidate()
        }
        button8.setOnClickListener {


            paint.color=Color.rgb(110,60,46)
            button8.invalidate()
        }
        button9.setOnClickListener {


            paint.color=Color.rgb(255,193,7)
            button9.invalidate()
        }
        button10.setOnClickListener {

            paint.color=Color.rgb(103,58,183)
            button10.invalidate()
        }
        button11.setOnClickListener {

            paint.color=Color.rgb(231,186,228)
            button11.invalidate()
        }
        button12.setOnClickListener {

            paint.color=Color.rgb(255,187,51)
            button12.invalidate()
        }
        buttonwider.setOnClickListener {

            paint.strokeWidth += 10f
            buttonwider.invalidate()
        }
        buttonnarrow.setOnClickListener {
            if (paint.strokeWidth>15f){
                paint.strokeWidth -= 10f
            }
            buttonnarrow.invalidate()
        }
        buttondeep.setOnClickListener {
            if(paint.alpha<235){
                paint.alpha+=20
            }
            buttondeep.invalidate()
        }
        buttonlight.setOnClickListener {

            if(paint.alpha>38){
                paint.alpha-=20
            }
            buttonlight.invalidate()
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
    fun goToActivityMain(view: View) {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }
    fun toggleEraser(view: View) {
        isErasing = !isErasing
        if (isErasing) {
            view.setBackgroundColor(Color.GRAY)
        } else {
            view.setBackgroundColor(Color.LTGRAY)
        }
    }
}