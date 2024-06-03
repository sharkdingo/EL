package com.example.el_work

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.ComponentActivity
import android.content.res.Configuration
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.el_work.MainUserActivity.MainUserActivity


class MainPage : ComponentActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationDrawer: ConstraintLayout
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        val imageView = findViewById<ImageView>(R.id.backgroundImage)
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val includeView = findViewById<View>(R.id.includeLayout) as ViewGroup
//        val mediaPlayer:MediaPlayer=MediaPlayer.create(this,R.raw.bgm)
//        mediaPlayer.isLooping=true
//        mediaPlayer.start()

        preferences = getSharedPreferences("app_settings", MODE_PRIVATE)
        val isMusicEnabled = preferences.getBoolean("music_enabled", true)

        val musicServiceIntent = Intent(this, MusicService::class.java)
        musicServiceIntent.putExtra("play_music", isMusicEnabled)
        startService(musicServiceIntent)

        val settingsButton = findViewById<Button>(R.id.settingsButton)
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        drawerLayout = findViewById(R.id.MainPage)
        navigationDrawer = findViewById(R.id.NavigationDrawer)

        val toggleButton: Button = findViewById(R.id.ToggleButton)
        toggleButton.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }

        setDynamicBackground(imageView)
        setDynamicInclude(layoutInflater, includeView)
        setDrawerWidth()

    }

    fun StartGame(view: View) {
        val intent = Intent(this, ClassifyActivity1::class.java)
        startActivity(intent)
    }

    fun goToActivity_main(view: View){
        val intent = Intent(this, ClassifyActivity1::class.java)
        startActivity(intent)
    }

    fun goToMainUser(view: View){
        val intent = Intent(this, MainUserActivity::class.java)
        startActivity(intent)
    }

    fun goToSetting(view: View){
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    private fun setDrawerWidth() {
        val params = navigationDrawer.layoutParams as DrawerLayout.LayoutParams
        params.width = (resources.displayMetrics.widthPixels * 0.75).toInt()
        navigationDrawer.layoutParams = params
    }

    private fun setDynamicInclude(layoutInflater: LayoutInflater, includeView: ViewGroup) {
        includeView.removeAllViews()
        val layoutId = if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            R.layout.game_start_button_h
        } else {
            R.layout.game_start_button_v
        }
        layoutInflater.inflate(layoutId, includeView)
    }

    private fun setDynamicBackground(imageView: ImageView) {
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            imageView.setImageResource(R.drawable.bg_horizontal)
        } else {
            imageView.setImageResource(R.drawable.bg_vertical)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val imageView = findViewById<ImageView>(R.id.backgroundImage)
        setDynamicBackground(imageView)
        val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val includeView = findViewById<ViewGroup>(R.id.includeLayout)
        setDynamicInclude(layoutInflater, includeView)
        setDrawerWidth()
    }
}