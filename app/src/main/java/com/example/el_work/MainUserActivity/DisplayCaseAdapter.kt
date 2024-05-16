package com.example.el_work.MainUserActivity

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.el_work.DataBase.DatabaseHelper
import com.example.el_work.R


class DisplayCaseAdapter(private val context: Context, private val cursor: Cursor) : RecyclerView.Adapter<DisplayCaseHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayCaseHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.display_case_item, parent, false)
        return DisplayCaseHolder(view)
    }

    override fun onBindViewHolder(holder: DisplayCaseHolder, position: Int) {
        if (cursor.moveToPosition(position)) {
            //  从数据库查询结果中获取名称字段的值，并存储到 name 变量中。
            val nameIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)
            if (nameIndex >= 0) {
                val name = cursor.getString(nameIndex)
                // 将从数据库中获取的名称数据设置到 ViewHolder 中的 TextView（用于显示名称）中。
                holder.itemName.text = name
            }
            //  从数据库查询结果中获取时间字段的值，并存储到 name 变量中。
            val timeIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_TIME)
            if (timeIndex >= 0) {
                val time = cursor.getString(timeIndex)
                // 将从数据库中获取的时间数据设置到 ViewHolder 中的 TextView（用于显示时间）中。
                holder.itemTime.text = time
            }
            val imageResIdIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_IMAGE_RES_ID)
            if (imageResIdIndex >= 0) {
                val imageResId = cursor.getInt(imageResIdIndex)
                // 将从数据库中获取的时间数据设置到 ViewHolder 中的 TextView（用于显示时间）中。
                // 根据资源ID加载图片
                val imageBitmap = getImageFromResId(imageResId)
                holder.itemImage.setImageBitmap(imageBitmap)
            }
        }

    }

    private fun getImageFromResId(imageResId: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, imageResId)
    }


    override fun getItemCount(): Int {
        return cursor.count
    }
}
