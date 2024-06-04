package com.example.el_work

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.el_work.dataBase.ImageRepository
import kotlin.random.Random

class FreeCreation : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    private val colors = mutableListOf<Int>()
    private lateinit var imageView: ImageView
    private lateinit var canvas: Canvas
    private lateinit var paint:Paint
    private lateinit var bitmap: Bitmap
    private lateinit var imageRepository: ImageRepository

    private var startX = 0f
    private var startY = 0f
    var isErasing = false

    @SuppressLint("ClickableViewAccessibility", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.free_creation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.freeCreation)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageRepository = ImageRepository(this)
        val saveButton: Button = findViewById(R.id.button_save_free)
        saveButton.setOnClickListener{
            saveBitmap()
        }

        val button1 = findViewById<Button>(R.id.button_red)
        val button2 = findViewById<Button>(R.id.button_green)
        val button3 = findViewById<Button>(R.id.button_blue)
        val button4 = findViewById<Button>(R.id.button_yellow)
        val button5 = findViewById<Button>(R.id.button_black)
        val button6 = findViewById<Button>(R.id.button_5)
        val button7 = findViewById<Button>(R.id.button_2)
        val button8 = findViewById<Button>(R.id.button_4)
        val button9 = findViewById<Button>(R.id.button_1)
        val button10 = findViewById<Button>(R.id.button_purple)
        val button11 = findViewById<Button>(R.id.button_pink)
        val button12 = findViewById<Button>(R.id.button_orange)
        val buttonwider = findViewById<Button>(R.id.button_wider)
        val buttonnarrow= findViewById<Button>(R.id.button_narrow)
        val buttondeep= findViewById<Button>(R.id.button_deep)
        val buttonlight = findViewById<Button>(R.id.button_light)
        var initialButtonColor = button4.backgroundTintList?.defaultColor ?: Color.BLACK
        val intro : TextView = findViewById<TextView>(R.id.intro_free);
        val sentences = arrayOf(
            "中国国画源远流长，是中国传统绘画艺术的重要组成部分。",
            "国画注重意境抒发，强调笔墨之间的意象和意蕴。",
            "中国国画所采用的工具主要有毛笔、宣纸、墨等。",
            "国画以水墨为主要表现形式，追求以简约的笔墨勾勒出深刻的表现力。",
            "中国国画的传统题材包括山水、花鸟、人物等，寄予了丰富的象征意义。",
            "国画强调“以形写神”，通过意境、情感、审美观念等表现画家的个性和艺术追求。",
            "传统国画注重写意和写生相结合，力求在写生的基础上表现出画家的审美情感。",
            "国画作品中常见的表现手法包括写意、工笔、精品等，展现了绘画技法的多样性。",
            "中国国画艺术在不同历史时期有着不同的发展轨迹，反映了社会文化的变迁和艺术风格的演变。",
            "学习国画需要不断练习，领悟其中的精髓，培养对传统文化和艺术的热爱。"

            // 更多句子...
        )
        val randomIndex = Random.nextInt(sentences.size)
        intro.text = sentences[randomIndex]

        imageView = findViewById(R.id.imageView5_free)
        val originalBitmap = BitmapFactory.decodeResource(resources, R.drawable.whitecanvas)
        bitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
        canvas.drawBitmap(originalBitmap, 0f, 0f, null)
        imageView.setImageBitmap(bitmap)

        imageView.setOnTouchListener { v, event ->
            val scaledX = event.x / v.width * bitmap.width
            val scaledY = event.y / v.height * bitmap.height

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startX = scaledX
                    startY = scaledY
                }
                MotionEvent.ACTION_MOVE -> {
                    if (isErasing) {
                        val clearPaint = Paint().apply {
                            xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                            isAntiAlias = true
                        }
                        canvas.drawCircle(scaledX, scaledY, 40f, clearPaint)
                        imageView.invalidate()
                    } else {
                        canvas.drawLine(startX, startY, scaledX, scaledY, paint)
                        startX = scaledX
                        startY = scaledY
                        imageView.invalidate()
                    }
                }
            }
            true
        }
        paint = Paint().apply {
            color = initialButtonColor
            strokeWidth = 15f
            style = Paint.Style.FILL
            alpha=128
        }

        button1.setOnClickListener {

            paint.color=Color.rgb(233,30,99)
            button1.invalidate()

        }

        button2.setOnClickListener {

            paint.color=Color.rgb(76,175,80)
            button2.invalidate()
        }

        button3.setOnClickListener {

            paint.color=Color.rgb(33,150,243)
            button3.invalidate()
        }
        button4.setOnClickListener {

            paint.color=Color.rgb(255,255,15)
            button4.invalidate()
        }
        button5.setOnClickListener {

            paint.color=Color.rgb(0,0,0)
            button5.invalidate()
        }
        button6.setOnClickListener {

            paint.color=Color.rgb(135,191,186)
            button6.invalidate()
        }
        button7.setOnClickListener {


            paint.color=Color.rgb(63,81,181)
            button7.invalidate()
        }
        button8.setOnClickListener {


            paint.color=Color.rgb(110,60,46)
            button8.invalidate()
        }
        button9.setOnClickListener {


            paint.color=Color.rgb(255,193,7)
            button9.invalidate()
        }
        button10.setOnClickListener {

            paint.color=Color.rgb(103,58,183)
            button10.invalidate()
        }
        button11.setOnClickListener {

            paint.color=Color.rgb(231,186,228)
            button11.invalidate()
        }
        button12.setOnClickListener {

            paint.color=Color.rgb(255,187,51)
            button12.invalidate()
        }
        buttonwider.setOnClickListener {
            if(paint.strokeWidth<30f){
                paint.strokeWidth += 5f
            }
            buttonwider.invalidate()
        }
        buttonnarrow.setOnClickListener {
            if (paint.strokeWidth>10f){
                paint.strokeWidth -= 5f
            }
            buttonnarrow.invalidate()
        }
        buttondeep.setOnClickListener {
            if(paint.alpha<235){
                paint.alpha+=20
            }
            buttondeep.invalidate()
        }
        buttonlight.setOnClickListener {

            if(paint.alpha>38){
                paint.alpha-=20
            }
            buttonlight.invalidate()
        }

        val editText = findViewById<EditText>(R.id.name_free)
        val rootView = findViewById<ConstraintLayout>(R.id.freeCreation)

        // 设置 EditText 的回车键监听器
        editText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard(editText)
                editText.clearFocus()
                true
            } else {
                false
            }
        }

        // 设置根视图的触摸监听器
        rootView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                hideKeyboard(editText)
                editText.clearFocus()
            }
            false
        }
    }

    // 隐藏键盘的函数
    private fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun saveBitmap() {
        val bitmap = Bitmap.createBitmap(imageView.width, imageView.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        imageView.draw(canvas)
        imageRepository.saveImage(bitmap)
    }

    fun goToActivity2(view: View) {
        val intent = Intent(this, ShanShuiModel::class.java)
        startActivity(intent)
    }
    fun goToActivityMain(view: View) {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }
    fun toggleEraser(view: View) {
        isErasing = !isErasing
        if (isErasing) {
            view.setBackgroundColor(Color.GRAY)
        } else {
            view.setBackgroundResource(R.drawable.button_bg)
        }
    }

    fun goToFreeCreation(view: View) {
        val intent = Intent(this,FreeCreation::class.java)
        startActivity(intent)
    }
}