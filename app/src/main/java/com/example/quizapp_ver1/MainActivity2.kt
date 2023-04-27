package com.example.quizapp_ver1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat


class MainActivity2 : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition:Int = 0
    private var mQuestionList:ArrayList<Question>? = null
    private var mSelect:Int = -1
    private var key:Int = 0

    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null

    private var tvOption1 : TextView? = null
    private var tvOption2 : TextView? = null
    private var tvOption3 : TextView? = null
    private var tvOption4 : TextView? = null
    private var btnSubmit : Button? = null
    private var tvScore : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        progressBar = findViewById(R.id.progressBarid)
        tvProgress = findViewById(R.id.progressTV)
        tvQuestion = findViewById(R.id.questionTv)
        ivImage = findViewById(R.id.imageTv)

        tvOption1 = findViewById(R.id.option1)
        tvOption2 = findViewById(R.id.option2)
        tvOption3 = findViewById(R.id.option3)
        tvOption4 = findViewById(R.id.option4)
        btnSubmit = findViewById(R.id.submitBtn)
        tvScore = findViewById(R.id.scoreId)

        mQuestionList = Contestant.getQuestion()
        setQuestion(mCurrentPosition)
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(currentPosition : Int) {
        defaultOptionsView()
        val question: Question = mQuestionList!![currentPosition]
        progressBar?.progress = mCurrentPosition + 1
        tvProgress?.text = (mCurrentPosition + 1).toString() + "/" + progressBar?.max.toString()
        ivImage?.setImageResource(question.image)
        tvQuestion?.text = question.question
        tvOption1?.text = question.option1
        tvOption2?.text = question.option2
        tvOption3?.text = question.option3
        tvOption4?.text = question.option4
        key=question.correction

        tvOption1?.setOnClickListener(this)
        tvOption2?.setOnClickListener(this)
        tvOption3?.setOnClickListener(this)
        tvOption4?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)
    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOption1?.let{
            options.add(0,it)
        }
        tvOption2?.let{
            options.add(1,it)
        }
        tvOption3?.let{
            options.add(2,it)
        }
        tvOption4?.let{
            options.add(3,it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#FFAF7A6D"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    private fun selectedOption(tv:TextView , selectedOptionNum:Int){
        defaultOptionsView()
        mSelect = selectedOptionNum
        tv.setTextColor(Color.parseColor("#FFE8C2"))
        tv.typeface= Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_border_objects
        )
    }

    private fun getTv(k: Int): TextView{
        if (k==1) return tvOption1!!
        else if (k==2) return tvOption2!!
        else if (k==3) return tvOption3!!
        else return tvOption4!!
    }

    private fun correctionChange(tv: TextView) {
        tv.setTextColor(Color.parseColor("#f6f4d2"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.correct_option_background
        )
    }

    private fun incorrectionChange(tv: TextView) {
        tv.setTextColor(Color.parseColor("#f6f4d2"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.wrong_option_background
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view:View?) {
        when(view?.id){
            R.id.option1 -> {
                tvOption1?.let {
                    selectedOption(it,1)
                }
            }
            R.id.option2 -> {
                tvOption2?.let {
                    selectedOption(it,2)
                }
            }
            R.id.option3 -> {
                tvOption3?.let {
                    selectedOption(it,3)
                }
            }
            R.id.option4 -> {
                tvOption4?.let {
                    selectedOption(it,4)
                }
            }
            R.id.submitBtn -> {
                if (mSelect == -1) {
                    Toast.makeText(this, "Please choose your answer", Toast.LENGTH_LONG).show()
                } else {
                    when (btnSubmit?.text) {
                        "SUBMIT" -> {
                            if (key == mSelect) {
                                User.incScore()
                                correctionChange(getTv(key))
                            } else {
                                correctionChange(getTv(key))
                                incorrectionChange(getTv(mSelect))
                            }
                            if (mCurrentPosition == mQuestionList!!.size - 1) {
                                btnSubmit?.text = "FINISH"
                            } else {
                                btnSubmit?.text = "NEXT"
                            }
                        }

                        "NEXT" -> {
                            mSelect = -1
                            mCurrentPosition += 1
                            btnSubmit?.text = "SUBMIT"
                            setQuestion(mCurrentPosition)
                        }

                        "FINISH" -> {
                            User.getUserName()?.let{
                                User.getUserScore()?.let{
                                    val currentPlayer : Player = Player (User.getUserName(),User.getUserScore())
                                    History.add(currentPlayer)
                                }
                            }
                            History.sortByDescending { it.Score }
                            val intent = Intent(this, MainActivity3::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}
