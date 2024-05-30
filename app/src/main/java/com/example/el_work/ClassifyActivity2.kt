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

class ClassifyActivity2 : AppCompatActivity() {
    private lateinit var bitmap: Bitmap
    private lateinit var dbHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
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

}