package com.example.el_work

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.el_work.MainUserActivity.MainUserActivity
import com.example.el_work.database.ImageRepository

class SettingActivity : AppCompatActivity() {
    private lateinit var imageRepository: ImageRepository
    @SuppressLint("MissingInflatedId", "UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.setting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.setting)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        imageRepository = ImageRepository(this)
        val switchClear: Switch = findViewById(R.id.setting_bottomLayout_switch4)
        switchClear.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                imageRepository.clearImage()
                Handler(Looper.getMainLooper()).postDelayed({
                    switchClear.isChecked = false
                }, 1000)
            }
        }

        val layoutButton: Button = findViewById(R.id.setting_topLayout_button)
        layoutButton.setOnClickListener {
            goToMainPage()
            finishAffinity()
        }

        val userButton: Button = findViewById(R.id.setting_bottomLayout_button1)
        userButton.setOnClickListener {
            goToMainUser()
        }
    }
    private fun goToMainPage() {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }
    private fun goToMainUser(){
        val intent = Intent(this, MainUserActivity::class.java)
        startActivity(intent)
    }

}