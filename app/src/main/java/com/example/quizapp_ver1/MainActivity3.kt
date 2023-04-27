package com.example.quizapp_ver1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        val button : Button = findViewById(R.id.replayBtn)
        val tvScore : TextView? = findViewById(R.id.scoreId)
        val tvName : TextView? = findViewById(R.id.nameId)

        User.getUserScore()?.let {
            tvScore?.text = (User.getUserScore()?.times(10)).toString()
        }
        tvName?.text=User.getUserName()

        val tvPlayer1Name : TextView? = findViewById(R.id.playerName1)
        val tvPlayer1Score : TextView? = findViewById(R.id.playerScore1)

        val tvPlayer2Name : TextView? = findViewById(R.id.playerName2)
        val tvPlayer2Score : TextView? = findViewById(R.id.playerScore2)

        val tvPlayer3Name : TextView? = findViewById(R.id.playerName3)
        val tvPlayer3Score : TextView? = findViewById(R.id.playerScore3)

        if (History.size >=1){
            tvPlayer1Name?.text=History[0].Name
            tvPlayer1Score?.text=(History[0].Score?.times(10)).toString()
        }
        if (History.size >=2){
            tvPlayer1Name?.text=History[0].Name
            tvPlayer1Score?.text=(History[0].Score?.times(10)).toString()
            tvPlayer2Name?.text=History[1].Name
            tvPlayer2Score?.text=(History[1].Score?.times(10)).toString()
        }
        if (History.size >=3) {
            tvPlayer1Name?.text=History[0].Name
            tvPlayer1Score?.text=(History[0].Score?.times(10)).toString()
            tvPlayer2Name?.text=History[1].Name
            tvPlayer2Score?.text=(History[1].Score?.times(10)).toString()
            tvPlayer3Name?.text=History[2].Name
            tvPlayer3Score?.text=(History[2].Score?.times(10)).toString()
        }
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}