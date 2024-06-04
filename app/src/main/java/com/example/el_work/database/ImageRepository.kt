package com.example.el_work.database

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.ByteArrayOutputStream
class ImageRepository(private val context: Context) {

    private val dbHelper = ImageDatabaseHelper(context)

    fun saveImage(bitmap: Bitmap) {
        val db = dbHelper.writableDatabase
        val byteArray = bitmapToByteArray(bitmap)
        val values = ContentValues().apply {
            put("image_data", byteArray)
        }
        db.insert("images", null, values)
        db.close()
    }
    private fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun getImageList(): List<Bitmap> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM images", null)
        val imageList = mutableListOf<Bitmap>()
        val columnIndex = cursor.getColumnIndex("image_data")
        if (columnIndex == -1) {
            Log.e("no image_data", "no image_data")
            return imageList
        }else{
            if (cursor.moveToFirst()) {
                do {
                    val imageData = cursor.getBlob(columnIndex)
                    val bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size)
                    imageList.add(bitmap)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
            return imageList
        }
    }
    fun clearImage() {
        val db = dbHelper.writableDatabase
        db.delete("images", null, null)
        db.close()
    }
}