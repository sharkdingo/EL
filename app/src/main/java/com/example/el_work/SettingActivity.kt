package com.example.el_work

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.el_work.MainUserActivity.MainUserActivity
import com.example.el_work.dataBase.ImageRepository

class SettingActivity : AppCompatActivity() {
    private lateinit var imageRepository: ImageRepository
    private lateinit var preferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.setting)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.setting)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        preferences = getSharedPreferences("app_settings", MODE_PRIVATE)
        editor = preferences.edit()

        val switchMusic = findViewById<SwitchCompat>(R.id.setting_bottomLayout_switch1)
        val isMusicEnabled = preferences.getBoolean("music_enabled", true)
        switchMusic.isChecked = isMusicEnabled

        switchMusic.setOnCheckedChangeListener { _, isChecked ->
            editor.putBoolean("music_enabled", isChecked)
            editor.apply()

            val musicServiceIntent = Intent(this, MusicService::class.java)
            musicServiceIntent.putExtra("play_music", isChecked)
            startService(musicServiceIntent)
        }
        imageRepository = ImageRepository(this)
        val switchClear: SwitchCompat = findViewById(R.id.setting_bottomLayout_switch4)
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