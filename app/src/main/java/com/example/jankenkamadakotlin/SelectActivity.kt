package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.TOP
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.SeekBar
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class SelectActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        CountApp().setBattleFormat(0)
        val MP = TableLayout.LayoutParams.MATCH_PARENT
        val WC = TableLayout.LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity

        val layout = ConstraintLayout(this)
        layout.layoutParams = LayoutParams(MP, MP)
        setContentView(layout)

        val linearLayout = LinearLayout(this).apply { id = View.generateViewId() }
        linearLayout.layoutParams = LayoutParams(MP, MP)
        linearLayout.orientation = LinearLayout.VERTICAL
        layout.addView(linearLayout)

        val textView = TextView(this)
        textView.layoutParams = LayoutParams(MP,WC)
        textView.text = "対戦形式"
        textView.gravity = CENTER;TOP
        (textView.layoutParams as LayoutParams).weight = 1F
        textView.textSize = 30F
        linearLayout.addView(textView)

        val linearLayout1 = LinearLayout(this)
        linearLayout1.layoutParams = LayoutParams(MP,WC)
        linearLayout1.orientation = LinearLayout.HORIZONTAL
        (linearLayout1.layoutParams as LayoutParams).weight = 1F
        linearLayout.addView(linearLayout1)

        val gameMode = TextView(this)
        gameMode.layoutParams = LayoutParams(WC,WC)
        (gameMode.layoutParams as LayoutParams).gravity = CENTER_VERTICAL
        gameMode.textSize = 40F
        gameMode.text = "総当たり戦"
        linearLayout1.addView(gameMode)

        val seekBar = SeekBar(this)
        seekBar.layoutParams = LayoutParams(MP,WC)
        (seekBar.layoutParams as LayoutParams).gravity = CENTER_VERTICAL
        seekBar.max = 1
        linearLayout1.addView(seekBar)

        val linearLayout2 = LinearLayout(this)
        linearLayout2.layoutParams = LayoutParams(MP,WC)
        linearLayout2.orientation = LinearLayout.HORIZONTAL
        (linearLayout2.layoutParams as LayoutParams).weight = 1F
        linearLayout.addView(linearLayout2)

        val gameCount = SeekBar(this)
        gameCount.layoutParams = LayoutParams(WC,WC)
        (gameCount.layoutParams as LayoutParams).gravity = CENTER_VERTICAL
        (gameCount.layoutParams as LayoutParams).weight = 1F
        gameCount.max = 9
        linearLayout2.addView(gameCount)

        val roundCount = TextView(this)
        roundCount.layoutParams = LayoutParams(WC,WC)
        (roundCount.layoutParams as LayoutParams).weight = 0F
        roundCount.textSize = 27F
        roundCount.text = "回数："+(CountApp().getCount())
        linearLayout2.addView(roundCount)

        val expRule = TextView(this)
        expRule.layoutParams = LayoutParams(MP,WC)
        (expRule.layoutParams as LayoutParams).weight = 1F
        expRule.textSize = 19F
        expRule.text = "ルール"
        linearLayout.addView(expRule)

        val rule = TextView(this)
        rule.layoutParams = LayoutParams(MP,WC)
        (rule.layoutParams as LayoutParams).weight = 5F
        rule.textSize = 19F
        rule.text = "1.対戦形式は任意で1～10まで対戦できます\n" +
                "2.対戦は勝敗に問わずカウントします\n" +
                "3.設定した値まで終了しません\n" +
                "4.結果は総合的に判断します"
        linearLayout.addView(rule)

        val gameStart = Button(this).apply { id = View.generateViewId() }
        gameStart.layoutParams = LayoutParams(WC,WC)
        gameStart.gravity = CENTER
        gameStart.textSize = 36F
        gameStart.text = "ゲームスタート"
        layout.addView(gameStart)

        val constraintSet = ConstraintSet()
        constraintSet.clone(layout)

        constraintSet.connect(linearLayout.id,ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT)
        constraintSet.connect(linearLayout.id,ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT)
        constraintSet.connect(linearLayout.id,ConstraintSet.TOP,ConstraintSet.PARENT_ID,ConstraintSet.TOP)
        constraintSet.connect(linearLayout.id,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM)

        constraintSet.connect(gameStart.id,ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT)
        constraintSet.connect(gameStart.id,ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT)
        constraintSet.connect(gameStart.id,ConstraintSet.BOTTOM,ConstraintSet.PARENT_ID,ConstraintSet.BOTTOM)

        constraintSet.applyTo(layout)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 == 1){
                    gameMode.text = "星取り戦　"
                    rule.text = "1.対戦形式は任意で1～10まで対戦できます\n" +
                            "2.設定した回戦数の半分以上を満たした場合終了します\n" +
                            "3.設定した回数を達した場合終了します\n"+
                            "4.設定した回数が半分以上があいこだった場合は引き分けとします"
                    CountApp().setBattleFormat(1)
                }else{
                    gameMode.text = "総当たり戦"
                    rule.text = "1.対戦形式は任意で1～10まで対戦できます\n" +
                            "2.対戦は勝敗に問わずカウントします\n" +
                            "3.設定した値まで終了しません\n" +
                            "4.結果は総合的に判断します"
                    CountApp().setBattleFormat(0)
                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        gameCount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                roundCount.text = "回数："+(p1+1)
                CountApp().setCount(p1+1)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        val intent = Intent(application,MainActivity::class.java)
        gameStart.setOnClickListener {
            startActivity(intent)
        }
    }
}