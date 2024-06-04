package com.example.el_work.MainUserActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.el_work.R
import com.example.el_work.database.ImageRepository

class MainUserActivity : AppCompatActivity() {

    // 展示柜相关变量
    private lateinit var recyclerView: RecyclerView
    private lateinit var imageRepository: ImageRepository
    private lateinit var imageView: ImageView
    private lateinit var bitmapAdapter: BitmapAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_user_interface)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_user_interface)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 展示柜RecyclerView
        imageRepository = ImageRepository(this)
        recyclerView = findViewById(R.id.recycler_view_zhan)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        bitmapAdapter = BitmapAdapter(this, imageRepository.getImageList(), recyclerView)
        recyclerView.adapter = bitmapAdapter
        recyclerView.addItemDecoration(BitmapItemDecoration(this))
    }
}