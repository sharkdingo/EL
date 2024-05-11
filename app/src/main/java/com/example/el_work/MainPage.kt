package com.example.el_work

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.ComponentActivity

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout


class MainPage : ComponentActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationDrawer: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainpage)

        drawerLayout = findViewById(R.id.MainPage)
        navigationDrawer = findViewById(R.id.NavigationDrawer)

        // 设置侧边栏的宽度为屏幕的75%
        val params = navigationDrawer.layoutParams as DrawerLayout.LayoutParams
        params.width = (resources.displayMetrics.widthPixels * 0.75).toInt()
        navigationDrawer.layoutParams = params

        // 设置ToggleButton的点击监听器
        val toggleButton: Button = findViewById(R.id.ToggleButton)
        toggleButton.setOnClickListener {
            // 根据抽屉的状态打开或关闭
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END)
            } else {
                drawerLayout.openDrawer(GravityCompat.END)
            }
        }
    }
    fun StartGame(view: View) {
        val intent = Intent(this, ClassifyActivity1::class.java)
        startActivity(intent)
    }
}