package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class HalfwayProgressActivity : AppCompatActivity() {

    @SuppressLint("StringFormatMatches", "SourceLockedOrientationActivity", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val mp = TableLayout.LayoutParams.MATCH_PARENT
        val wc = TableLayout.LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity

        val countWin:Int = CountApp().getWinCount()
        val countLose:Int = CountApp().getLoseCount()
        val countDraw:Int = CountApp().getDrawCount()

        val layout = ConstraintLayout(this)
        layout.layoutParams = LayoutParams(mp,mp)
        layout.setPadding(20,20,20,20)
        setContentView(layout)

        val linearLayout = LinearLayout(this).apply { id = View.generateViewId() }
        linearLayout.layoutParams = LayoutParams(mp,mp)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.weightSum = 12F
        layout.addView(linearLayout)

        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        constraintSet.connect(linearLayout.id,
            ConstraintSet.LEFT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.LEFT)
        constraintSet.connect(linearLayout.id,
            ConstraintSet.RIGHT,
            ConstraintSet.PARENT_ID,
            ConstraintSet.RIGHT)
        constraintSet.connect(linearLayout.id,
            ConstraintSet.TOP,
            ConstraintSet.PARENT_ID,
            ConstraintSet.TOP)
        constraintSet.connect(linearLayout.id,
            ConstraintSet.BOTTOM,
            ConstraintSet.PARENT_ID,
            ConstraintSet.BOTTOM)

        constraintSet.applyTo(layout)

        val textView = TextView(this)
        textView.layoutParams = LayoutParams(mp,wc)
        textView.text = getString(R.string.halfway_title)
        textView.textSize = 30F
        textView.gravity = Gravity.CENTER
        (textView.layoutParams as LayoutParams).weight = 2F
        linearLayout.addView(textView)

        val battleCount = TextView(this)
        battleCount.layoutParams = LayoutParams(mp,wc)
        battleCount.gravity = Gravity.CENTER
        (battleCount.layoutParams as LayoutParams).weight = 3F
        battleCount.textSize = 30F
        battleCount.text = ""+CountApp().getAddCount()+"戦目だよ"
        linearLayout.addView(battleCount)

        val winCount = TextView(this)
        winCount.layoutParams = LayoutParams(mp,wc)
        (winCount.layoutParams as LayoutParams).weight = 1F
        winCount.text = "勝った数：$countWin"
        linearLayout.addView(winCount)

        val loseCount = TextView(this)
        loseCount.layoutParams = LayoutParams(mp,wc)
        (loseCount.layoutParams as LayoutParams).weight = 1F
        loseCount.text = "負けた数：$countLose"
        linearLayout.addView(loseCount)

        val drawCount = TextView(this)
        drawCount.layoutParams = LayoutParams(mp,wc)
        (drawCount.layoutParams as LayoutParams).weight = 1F
        drawCount.text = "引き分け数：$countDraw"
        linearLayout.addView(drawCount)

        val button = Button(this)
        button.layoutParams = LayoutParams(mp,wc)
        (button.layoutParams as LayoutParams).weight = 0F
        button.text = "次の対戦へ"
        linearLayout.addView(button)

        val intent = Intent(application,MainActivity::class.java)
        button.setOnClickListener {
            startActivity(intent)
        }
    }

}