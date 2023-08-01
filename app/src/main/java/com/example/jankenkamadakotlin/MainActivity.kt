package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TableLayout.HORIZONTAL
import android.widget.TableLayout.LayoutParams
import android.widget.TableLayout.VERTICAL
import android.widget.TextView
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val count:Int = CountApp().getAddCount()
        val guHand = 0
        val chHand = 1
        val paHand = 2

        val layMp = LayoutParams.MATCH_PARENT
        val layWc = LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity

        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = LayoutParams(layMp,layMp)
        linearLayout.orientation = VERTICAL
        linearLayout.weightSum = 7F
        setContentView(linearLayout)

        val textView = TextView(this)
        textView.layoutParams = LayoutParams(layMp,layWc)
        textView.setPadding(20)
        textView.gravity = Gravity.CENTER
        if (count == 0) {
            textView.text = getString(R.string.title)
        }else{
            textView.text = "第"+(count+1)+"戦目：じゃーんけーん......"
        }
        textView.textSize = 30F
        (textView.layoutParams as LinearLayout.LayoutParams).weight = 1F
        linearLayout.addView(textView)

        val imageView = ImageView(this)
        imageView.layoutParams = LayoutParams(layMp,layWc)
        imageView.setImageResource(R.drawable.main)
        (imageView.layoutParams as LinearLayout.LayoutParams).weight = 2F
        linearLayout.addView(imageView)

        val tapButton = LinearLayout(this)
        tapButton.layoutParams = LinearLayout.LayoutParams(layMp,0)
        tapButton.orientation = HORIZONTAL
        (tapButton.layoutParams as LinearLayout.LayoutParams).weight = 3F
        linearLayout.addView(tapButton)

        val gu = ImageButton(this)
        gu.layoutParams = LayoutParams(layWc,layWc)
        (gu.layoutParams as LinearLayout.LayoutParams).weight = 1F
        gu.setImageResource(R.drawable.j_gu02)
        gu.background = getDrawable(R.color.transparent)
        gu.scaleType = ImageView.ScaleType.FIT_CENTER
        tapButton.addView(gu)

        val ch = ImageButton(this)
        ch.layoutParams = LayoutParams(layWc,layWc)
        (ch.layoutParams as LinearLayout.LayoutParams).weight = 1F
        ch.setImageResource(R.drawable.j_ch02)
        ch.background = getDrawable(R.color.transparent)
        ch.scaleType = ImageView.ScaleType.FIT_CENTER
        tapButton.addView(ch)

        val pa = ImageButton(this)
        pa.layoutParams = LayoutParams(layWc,layWc)
        (pa.layoutParams as LinearLayout.LayoutParams).weight = 1F
        pa.setImageResource(R.drawable.j_pa02)
        pa.background = getDrawable(R.color.transparent)
        pa.scaleType = ImageView.ScaleType.FIT_CENTER
        tapButton.addView(pa)

        val subtitle = TextView(this)
        subtitle.layoutParams = LayoutParams(layMp,layWc)
        (subtitle.layoutParams as LinearLayout.LayoutParams).weight = 1F
        subtitle.gravity = Gravity.CENTER
        subtitle.text = "画像をタップしてね"
        subtitle.textSize = 30F
        linearLayout.addView(subtitle)

        val intent = Intent(application,ResultActivity::class.java)
        gu.setOnClickListener {
            intent.putExtra("hand",guHand)
            startActivity(intent)
        }
        ch.setOnClickListener {
            intent.putExtra("hand",chHand)
            startActivity(intent)
        }
        pa.setOnClickListener {
            intent.putExtra("hand",paHand)
            startActivity(intent)
        }
    }
}