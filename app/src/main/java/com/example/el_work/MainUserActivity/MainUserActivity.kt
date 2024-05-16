package com.example.el_work.MainUserActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.el_work.DataBase.DatabaseHelper
import com.example.el_work.R

class MainUserActivity : AppCompatActivity() {

    // 展示柜相关变量
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DisplayCaseAdapter
    private lateinit var databaseHelper: DatabaseHelper

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
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)

        // 数据库
        databaseHelper = DatabaseHelper(this)
        val cursor = databaseHelper.getAllData()
        adapter = DisplayCaseAdapter(this, cursor)
        recyclerView.adapter = adapter
    }

    // 关闭数据库
    override fun onDestroy() {
        super.onDestroy()
        databaseHelper.close()
    }

}