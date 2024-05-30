package com.example.el_work.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 数据库帮助类
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val createDrawingsTable = "CREATE TABLE drawings (id INTEGER PRIMARY KEY AUTOINCREMENT, image_path TEXT NOT NULL)"
        val createDisplayCaseTable = "CREATE TABLE $DISPLAY_CASE_TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_TIME TEXT, " +
                "$COLUMN_IMAGE_RES_ID INTEGER, " +
                "$COLUMN_DESCRIPTION TEXT)"
        db.execSQL(createDrawingsTable)
        db.execSQL(createDisplayCaseTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $DISPLAY_CASE_TABLE_NAME")
        onCreate(db)
    }

    fun insertData(name: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_NAME, name)
        db.insert(DISPLAY_CASE_TABLE_NAME, null, contentValues)
        db.close()
    }

    fun getAllData(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $DISPLAY_CASE_TABLE_NAME", null)
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MyDatabase"
        private const val TABLE_NAME = "drawings"
        private const val COLUMN_ID = "id"
        private const val COLUMN_IMAGE_PATH = "image_path"
        private const val DISPLAY_CASE_TABLE_NAME = "DisplayCaseTable"
        const val COLUMN_NAME = "name"
        const val COLUMN_TIME = "time"
        const val COLUMN_IMAGE_RES_ID = "image_res_id"
        private const val COLUMN_DESCRIPTION = "description"
    }
}
