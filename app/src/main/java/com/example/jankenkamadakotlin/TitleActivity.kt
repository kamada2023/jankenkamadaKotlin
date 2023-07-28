package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.Gravity.TOP
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class TitleActivity : AppCompatActivity() {
    @SuppressLint("SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val MP = TableLayout.LayoutParams.MATCH_PARENT
        val WC = TableLayout.LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity

        val countApp = CountApp()

        val layout = ConstraintLayout(this)
        layout.layoutParams = LayoutParams(MP,MP)
        setContentView(layout)

        val button = Button(this).apply { id = View.generateViewId() }
        button.layoutParams = LayoutParams(MP,WC)
        (button.layoutParams as LayoutParams).gravity = CENTER
        button.text = "セレクト画面へ"
        button.textSize = 36F
        layout.addView(button)

        val linearLayout = LinearLayout(this).apply { id = View.generateViewId() }
        linearLayout.layoutParams = LayoutParams(MP,WC)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.weightSum = 10F
        linearLayout.gravity = TOP ; CENTER
        layout.addView(linearLayout)

        val title = ImageView(this)
        title.layoutParams = LayoutParams(WC,WC)
        title.adjustViewBounds = true
        title.setImageResource(R.drawable.title)
        linearLayout.addView(title)

        val imageView = ImageView(this)
        imageView.layoutParams = LayoutParams(WC,WC)
        imageView.setImageResource(R.drawable.main)
        (imageView.layoutParams as LayoutParams).gravity = CENTER
        linearLayout.addView(imageView)

        val textView1 = TextView(this)
        textView1.layoutParams = LayoutParams(WC,WC)
        (textView1.layoutParams as LayoutParams).weight = 1F
        (textView1.layoutParams as LayoutParams).gravity = CENTER
        textView1.text = "僕と勝負だ！！"
        textView1.textSize = 40F
        linearLayout.addView(textView1)

        val textView2 = TextView(this)
        textView2.layoutParams = LayoutParams(WC,WC)
        (textView2.layoutParams as LayoutParams).weight = 1F
        (textView2.layoutParams as LayoutParams).gravity = CENTER
        textView2.text = ""+countApp.getNumOfWins()+"勝ち"+
            countApp.getNumOfLoses()+"負け"+countApp.getNumOfDraws()+"引き分け"
        textView2.textSize = 20F
        linearLayout.addView(textView2)

        val reset = Button(this)
        reset.layoutParams = LayoutParams(WC,WC)
        (reset.layoutParams as LayoutParams).weight = 1F
        (reset.layoutParams as LayoutParams).gravity = CENTER
        reset.text = "RESET BUTTON"
        reset.textSize = 27F
        linearLayout.addView(reset)

        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        constraintSet.connect(button.id,ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT)
        constraintSet.connect(button.id,ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT)
        constraintSet.connect(button.id,ConstraintSet.TOP,linearLayout.id,ConstraintSet.BOTTOM)
        constraintSet.connect(button.id,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM)

        constraintSet.connect(linearLayout.id,ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT)
        constraintSet.connect(linearLayout.id,ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT)
        constraintSet.connect(linearLayout.id,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP)
        constraintSet.connect(linearLayout.id,ConstraintSet.BOTTOM,button.id,ConstraintSet.TOP)

        constraintSet.applyTo(layout)

        reset.setOnClickListener {
            CountApp().clearTotalResult()
            textView2.text = ""+countApp.getNumOfWins()+"勝ち"+
                    countApp.getNumOfLoses()+"負け"+countApp.getNumOfDraws()+"引き分け"
        }
        val intent = Intent(application,SelectActivity::class.java)
        button.setOnClickListener {
            startActivity(intent)
        }
    }
}