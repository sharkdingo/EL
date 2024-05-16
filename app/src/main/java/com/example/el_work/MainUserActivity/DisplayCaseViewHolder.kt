package com.example.el_work.MainUserActivity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.el_work.R

//class DisplayCaseViewHolder : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_display_case_view_holder)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}
class DisplayCaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val itemName: TextView = itemView.findViewById(R.id.item_name)
    val itemTime: TextView = itemView.findViewById(R.id.item_time)
    val itemImage: ImageView = itemView.findViewById(R.id.item_image)
}

