package com.example.el_work

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ChoosePlayModel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun goToActivity2(view: View) {
        val intent = Intent(this, ShanShuiModel::class.java)
        startActivity(intent)
    }

    fun goToActivityHuaNiao(view: View) {
        val intent = Intent(this, HuaNiaoModel::class.java)
        startActivity(intent)
    }

    fun goToActivityQiWu(view: View) {
        val intent = Intent(this, QiWuModel::class.java)
        startActivity(intent)
    }

    fun goToFreeCreation(view: View) {
        val intent = Intent(this,FreeCreation::class.java)
        startActivity(intent)
    }
}