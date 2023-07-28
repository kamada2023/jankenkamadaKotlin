package com.example.jankenkamadakotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.Gravity.CENTER_HORIZONTAL
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.VERTICAL
import android.widget.TableLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import kotlin.random.Random

class ResultActivity : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val MP = TableLayout.LayoutParams.MATCH_PARENT
        val WC = TableLayout.LayoutParams.WRAP_CONTENT
        //val dp = resources.displayMetrics.density
        //val sp = resources.displayMetrics.scaledDensity

        val linearLayout = LinearLayout(this)
        linearLayout.layoutParams = LayoutParams(MP,MP)
        linearLayout.orientation = VERTICAL
        linearLayout.setPadding(10)
        setContentView(linearLayout)

        val imageView = ImageView(this)
        imageView.layoutParams = LayoutParams(MP,WC)
        (imageView.layoutParams as LayoutParams).gravity = CENTER
        linearLayout.addView(imageView)

        val frameLayout = FrameLayout(this)
        frameLayout.layoutParams = LayoutParams(MP, WC)
        (frameLayout.layoutParams as LayoutParams).weight = 0.1F
        linearLayout.addView(frameLayout)

        val cpu = TextView(this)
        cpu.layoutParams = ViewGroup.LayoutParams(WC,WC)
        cpu.text = "相手"
        frameLayout.addView(cpu)

        val cpuHands = ImageView(this)
        cpuHands.layoutParams = LayoutParams(MP,WC)
        (cpuHands.layoutParams as LayoutParams).gravity = CENTER
        frameLayout.addView(cpuHands)

        val result = TextView(this)
        result.layoutParams = LayoutParams(MP,WC)
        result.gravity = CENTER
        result.textSize = 30F
        linearLayout.addView(result)

        val frameLayout2 = FrameLayout(this)
        frameLayout2.layoutParams = LayoutParams(MP, WC)
        (frameLayout2.layoutParams as LayoutParams).weight = 0.1F
        linearLayout.addView(frameLayout2)

        val user = TextView(this)
        user.layoutParams = LayoutParams(WC,WC)
        user.text = "自分"
        frameLayout2.addView(user)

        val userHands = ImageView(this)
        userHands.layoutParams = LayoutParams(MP,WC)
        (userHands.layoutParams as LayoutParams).gravity = CENTER
        frameLayout2.addView(userHands)

        val nextBattle = Button(this)
        nextBattle.layoutParams = LayoutParams(MP,WC)
        (nextBattle.layoutParams as LayoutParams).gravity = CENTER_HORIZONTAL
        nextBattle.typeface = Typeface.DEFAULT_BOLD
        (nextBattle.layoutParams as LayoutParams).weight = 0F
        linearLayout.addView(nextBattle)
//cpu
        val seed : Long = System.currentTimeMillis()
        val r :Int = Random(seed).nextInt(3)
//user
        val id: Int = intent.getIntExtra("hand",0)
        CountApp().setAddCount()

        if (id==r){
            if (CountApp().getAddCount() <= CountApp().getCount()){
                CountApp().setDrawCount()
            }
            imageView.setImageResource(R.drawable.draw)
            result.text = "引き分け"
        }else if ((id==2 && r==0) || ((id+1)==r)){
            if (CountApp().getAddCount() <= CountApp().getCount()){
                CountApp().setWinCount()
            }
            imageView.setImageResource(R.drawable.win)
            result.text = "あんたの勝ち！！"
        }else{
            if (CountApp().getAddCount() <= CountApp().getCount()){
                CountApp().setLoseCount()
            }
            imageView.setImageResource(R.drawable.lose)
            result.text = "あなたの負け.."
        }

        when(r) {
            0 -> cpuHands.setImageResource(R.drawable.j_gu02)
            1 -> cpuHands.setImageResource(R.drawable.j_ch02)
            else -> cpuHands.setImageResource(R.drawable.j_pa02)
        }

        when(id) {
            0 -> userHands.setImageResource(R.drawable.j_gu02)
            1 -> userHands.setImageResource(R.drawable.j_ch02)
            else -> userHands.setImageResource(R.drawable.j_pa02)
        }

        nextBattle.text = if (CountApp().getCount() == CountApp().getAddCount()) {
            "リザルト画面へ"
        }else if (CountApp().getAddCount() == 1){
            "次の対戦へ"
        }else{
            "次のシーンへ"
        }

        nextBattle.setOnClickListener{
            continueOrEnd()
        }
    }

    private fun continueOrEnd(){
        var conOrEnd:Intent
        val first = Intent(application,MainActivity::class.java)
        val end = Intent(application,FinalResultActivity::class.java)
        val con = Intent(application,HalfwayProgressActivity::class.java)
        val game:Int = CountApp().getCount()
        val rounds:Int = CountApp().getAddCount()
        val win:Int = CountApp().getWinCount()
        val lose:Int = CountApp().getLoseCount()

        conOrEnd = if (rounds==1){ first }
        else{ con }

        if (CountApp().getBattleFormat() == 1){
            if ((win-lose) > (game-rounds)){
                conOrEnd = end
            }else if ((lose-win) > (game-rounds)){
                conOrEnd = end
            }else if (CountApp().getDrawCount() > (game/2)){
                conOrEnd = end
            }
        }

        if (game==rounds){
            conOrEnd = end
        }else if (game < rounds){
            conOrEnd = end
        }

        startActivity(conOrEnd)
    }
}