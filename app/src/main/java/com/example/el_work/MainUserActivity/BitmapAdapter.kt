package com.example.el_work.MainUserActivity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.el_work.ClassifyActivity2
import com.example.el_work.R
import java.io.ByteArrayOutputStream

class BitmapAdapter(private val context: Context, private val bitmapList: List<Bitmap>, private val recyclerView: RecyclerView) :
    RecyclerView.Adapter<BitmapAdapter.BitmapViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BitmapViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_bitmap, parent, false)
        return BitmapViewHolder(view)
    }

    override fun onBindViewHolder(holder: BitmapViewHolder, position: Int) {
        val bitmap = bitmapList[position]
        holder.imageView.setImageBitmap(bitmap)

        val recyclerViewHeight = recyclerView.height // 获取RecyclerView的高度
        val layoutParams = holder.imageView.layoutParams
        layoutParams.height = recyclerViewHeight

        // 计算ImageView的宽度，使其与高度成比例布局
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
        val aspectRatio = bitmapWidth.toFloat() / bitmapHeight.toFloat()
        val imageViewWidth = (recyclerViewHeight * aspectRatio).toInt()
        layoutParams.width = imageViewWidth
        holder.imageView.layoutParams = layoutParams
        holder.imageView.requestLayout()

        //设置点击事件
        holder.imageView.setOnClickListener {
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 60, outputStream)
            val byteArray = outputStream.toByteArray()
            val intent = Intent(context, ClassifyActivity2::class.java)
            intent.putExtra("bitmap", byteArray)
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return bitmapList.size
    }

    inner class BitmapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view_holder)
    }
}
