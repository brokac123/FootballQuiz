package com.lukabakula.nogometnikvizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    // TODO (STEP 2: A global variables for current position and questions list.)
    // START
    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName: String? = null
    // END


    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor

        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        super.onCreate(savedInstanceState)

        // This is used to align the xml view to this class
        setContentView(R.layout.activity_quiz_questions)

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val iv_image=findViewById<ImageView>(R.id.iv_image)
        val btn_submit=findViewById<Button>(R.id.btn_submit)

        // TODO (STEP 3: Make the questions list and the current position variable global and remove the logs here.)
        // START
        mUserName=intent.getStringExtra(Constants.USER_NAME)
        mQuestionsList = Constants.getQuestions()
        // END

        setQuestion()


        // TODO (STEP 6: Set all the click events for Options using the interface onClick listener)
        // START
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

        // END
    }

    override fun onClick(v: View?) {

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val iv_image=findViewById<ImageView>(R.id.iv_image)
        val btn_submit=findViewById<Button>(R.id.btn_submit)

        when (v?.id) {

            R.id.tv_option_one -> {

                selectedOptionView(tv_option_one, 1)
            }

            R.id.tv_option_two -> {

                selectedOptionView(tv_option_two, 2)
            }

            R.id.tv_option_three -> {

                selectedOptionView(tv_option_three, 3)
            }

            R.id.tv_option_four -> {

                selectedOptionView(tv_option_four, 4)
            }
            R.id.btn_submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            val intent= Intent(this,ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME,mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS,mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS,mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                            //   Toast.makeText(this@QuizQuestionsActivity, "You have successfully completed the quiz.", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    }else{
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    // TODO (STEP 5 : Lets create a function to set the question in the UI components which we have done earlier the onCreate method. And make some of the variables global which we will be using later.)
    // START
    /**
     * A function for setting the question to UI components.
     */
    private fun setQuestion() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val iv_image=findViewById<ImageView>(R.id.iv_image)
        val btn_submit=findViewById<Button>(R.id.btn_submit)


        val question =
            mQuestionsList!!.get(mCurrentPosition - 1) // Getting the question from the list with the help of current position.

        defaultOptionsView()

        progressBar.progress = mCurrentPosition
        tv_progress.text = "$mCurrentPosition" + "/" + progressBar.getMax()

        tv_question.text = question.question
        iv_image.setImageResource(question.image)
        tv_option_one.text = question.optionOne
        tv_option_two.text = question.optionTwo
        tv_option_three.text = question.optionThree
        tv_option_four.text = question.optionFour
    }
    // END

    // TODO (STEP 7: Create a function for view for highlighting the selected option.)
    // START
    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#363A43")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this@QuizQuestionsActivity,
            R.drawable.selected_option_border_bg
        )
    }

    // TODO (STEP 4: Create a function to set default options view.)
    // START
    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val iv_image=findViewById<ImageView>(R.id.iv_image)
        val btn_submit=findViewById<Button>(R.id.btn_submit)

        val options = ArrayList<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this@QuizQuestionsActivity,
                R.drawable.default_option_border_bg
            )
        }
    }
    private fun answerView(answer: Int, drawableView: Int) {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val tv_progress = findViewById<TextView>(R.id.tv_progress)
        val tv_question = findViewById<TextView>(R.id.tv_question)
        val tv_option_one = findViewById<TextView>(R.id.tv_option_one)
        val tv_option_two = findViewById<TextView>(R.id.tv_option_two)
        val tv_option_three = findViewById<TextView>(R.id.tv_option_three)
        val tv_option_four = findViewById<TextView>(R.id.tv_option_four)
        val iv_image=findViewById<ImageView>(R.id.iv_image)
        val btn_submit=findViewById<Button>(R.id.btn_submit)

        when (answer) {

            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(
                    this@QuizQuestionsActivity,
                    drawableView
                )
            }
        }
    }


}













