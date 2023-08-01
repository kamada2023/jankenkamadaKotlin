package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.VERTICAL
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class FinalResultActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layMp:Int = TableLayout.LayoutParams.MATCH_PARENT
        val layWc:Int = TableLayout.LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity

        val countWin:Int = CountApp().getWinCount()
        val countLose:Int = CountApp().getLoseCount()
        val countDraw:Int = CountApp().getDrawCount()

        val layout = ConstraintLayout(this)
        layout.layoutParams = LayoutParams(layMp,layMp)
        layout.setPadding(20,20,20,20)
        setContentView(layout)

        val linearLayout = LinearLayout(this).apply { id = View.generateViewId() }
        linearLayout.layoutParams = LayoutParams(layMp,layMp)
        linearLayout.orientation = VERTICAL
        linearLayout.weightSum = 10F
        setContentView(linearLayout)

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
        textView.layoutParams = LayoutParams(layMp,layWc)
        (textView.layoutParams as LayoutParams).weight = 2F
        textView.textSize = 30F
        textView.gravity = CENTER
        textView.text = "結果発表！！"
        linearLayout.addView(textView)

        val resultDrawFinal = ImageView(this)
        resultDrawFinal.layoutParams = LayoutParams(layMp,layWc)
        (resultDrawFinal.layoutParams as LayoutParams).weight = 1F
        linearLayout.addView(resultDrawFinal)

        val finCountWin = TextView(this)
        finCountWin.layoutParams = LayoutParams(layMp,layWc)
        finCountWin.textSize = 20F
        (finCountWin.layoutParams as LayoutParams).weight = 1F
        finCountWin.text = "勝った数：$countWin"
        linearLayout.addView(finCountWin)

        val finCountLose = TextView(this)
        finCountLose.layoutParams = LayoutParams(layMp,layWc)
        finCountLose.textSize = 20F
        (finCountLose.layoutParams as LayoutParams).weight = 1F
        finCountLose.text = "負けた数：$countLose"
        linearLayout.addView(finCountLose)

        val finCountDraw = TextView(this)
        finCountDraw.layoutParams = LayoutParams(layMp,layWc)
        finCountDraw.textSize = 20F
        (finCountDraw.layoutParams as LayoutParams).weight = 1F
        finCountDraw.text = "引き分け数：$countDraw"
        linearLayout.addView(finCountDraw)

        val backTitle = Button(this)
        backTitle.layoutParams = LayoutParams(layMp,layWc)
        (backTitle.layoutParams as LayoutParams).weight = 0F
        backTitle.text = "タイトルへ"
        linearLayout.addView(backTitle)

        if (countWin > countLose){
            resultDrawFinal.setImageResource(R.drawable.youwin)
            CountApp().setNumOfWins()
        }else if (countLose > countWin){
            resultDrawFinal.setImageResource(R.drawable.youlose)
            CountApp().setNumOfLoses()
        }else{
            resultDrawFinal.setImageResource(R.drawable.drawgame)
            CountApp().setNumOfDraws()
        }

        if (CountApp().getBattleFormat()==1){
            if (countDraw > (CountApp().getCount()/2)){
                resultDrawFinal.setImageResource(R.drawable.drawgame)
                CountApp().setNumOfDraws()
            }
        }

        val intent = Intent(application,TitleActivity::class.java)
        backTitle.setOnClickListener {
            CountApp().clearResult()
            startActivity(intent)
        }
    }
}