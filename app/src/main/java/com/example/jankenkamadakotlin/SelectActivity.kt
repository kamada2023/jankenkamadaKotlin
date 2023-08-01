package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Gravity.BOTTOM
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.Gravity.CENTER_VERTICAL
import android.view.Gravity.TOP
import android.widget.Button
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.SeekBar
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM


class SelectActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "SourceLockedOrientationActivity", "RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        CountApp().setBattleFormat(0)
        val layMp:Int = TableLayout.LayoutParams.MATCH_PARENT
        val layWc:Int = TableLayout.LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity


        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = LayoutParams(layMp, layMp)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.weightSum = 15F
        setContentView(linearLayout)

        val textView = TextView(this)
        textView.layoutParams = LayoutParams(layMp,layWc)
        textView.text = "対戦形式"
        textView.gravity = CENTER_HORIZONTAL
        (textView.layoutParams as LayoutParams).weight = 5F
        (textView.layoutParams as LayoutParams).gravity = TOP
        textView.textSize = 30F
        linearLayout.addView(textView)

        val linearLayout1 = LinearLayout(this)
        linearLayout1.layoutParams = LayoutParams(layMp,layWc)
        linearLayout1.orientation = LinearLayout.HORIZONTAL
        (linearLayout1.layoutParams as LayoutParams).weight = 1F
        linearLayout1.setPadding(20,0,20,0)
        linearLayout.addView(linearLayout1)

        val gameMode = TextView(this)
        gameMode.layoutParams = LayoutParams(layWc,layWc)
        (gameMode.layoutParams as LayoutParams).gravity = CENTER_VERTICAL
        gameMode.textSize = 40F
        gameMode.text = "総当たり戦"
        linearLayout1.addView(gameMode)

        val seekBar = SeekBar(this)
        seekBar.layoutParams = LayoutParams(layMp,layWc)
        (seekBar.layoutParams as LayoutParams).gravity = CENTER_VERTICAL
        seekBar.scrollBarSize = 29
        seekBar.max = 1
        linearLayout1.addView(seekBar)

        val linearLayout2 = LinearLayout(this)
        linearLayout2.layoutParams = LayoutParams(layMp,layWc)
        linearLayout2.orientation = LinearLayout.HORIZONTAL
        (linearLayout2.layoutParams as LayoutParams).weight = 1F
        linearLayout.addView(linearLayout2)

        val gameCount = SeekBar(this)
        gameCount.layoutParams = LayoutParams(layWc,layWc)
        (gameCount.layoutParams as LayoutParams).gravity = CENTER_VERTICAL
        (gameCount.layoutParams as LayoutParams).weight = 1F
        gameCount.scrollBarSize = 29
        gameCount.max = 9
        linearLayout2.addView(gameCount)

        val roundCount = TextView(this)
        roundCount.layoutParams = LayoutParams(layWc,layWc)
        (roundCount.layoutParams as LayoutParams).weight = 0F
        roundCount.textSize = 27F
        roundCount.text = "回数："+(CountApp().getCount())
        linearLayout2.addView(roundCount)

        val expRule = TextView(this)
        expRule.layoutParams = LayoutParams(layMp,layWc)
        (expRule.layoutParams as LayoutParams).weight = 1F
        expRule.textSize = 19F
        expRule.text = "ルール"
        linearLayout.addView(expRule)

        val rule = AppCompatTextView(this)
        rule.layoutParams = LayoutParams(layMp,layWc)
        (rule.layoutParams as LayoutParams).weight = 6F
        rule.setAutoSizeTextTypeWithDefaults(AUTO_SIZE_TEXT_TYPE_UNIFORM)
        rule.maxLines = 6
        rule.textSize = 19F
        rule.text = "1.対戦形式は任意で1～10まで対戦できます\n" +
                "2.対戦は勝敗に問わずカウントします\n" +
                "3.設定した値まで終了しません\n" +
                "4.結果は総合的に判断します\n\n\n"
        linearLayout.addView(rule)

        val gameStart = Button(this)
        gameStart.layoutParams = LayoutParams(layMp,layWc)
        (gameStart.layoutParams as LayoutParams).weight = 0F
        (gameStart.layoutParams as LayoutParams).setMargins(20,0,20,0)
        gameStart.gravity = CENTER
        (gameStart.layoutParams as LayoutParams).gravity = BOTTOM
        gameStart.textSize = 36F
        gameStart.text = "ゲームスタート"
        linearLayout.addView(gameStart)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 == 1){
                    gameMode.text = "星取り　戦"
                    rule.text = "1.対戦形式は任意で1～10まで対戦できます\n" +
                            "2.設定した回戦数の半分以上を満たした\n" +
                            "場合終了します\n" +
                            "3.設定した回数を達した場合終了します\n"+
                            "4.設定した回数が半分以上があいこだった\n" +
                            "場合は引き分けとします"
                    CountApp().setBattleFormat(1)
                }else{
                    gameMode.text = "総当たり戦"
                    rule.text = "1.対戦形式は任意で1～10まで対戦できます\n" +
                            "2.対戦は勝敗に問わずカウントします\n" +
                            "3.設定した値まで終了しません\n" +
                            "4.結果は総合的に判断します\n\n\n"
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