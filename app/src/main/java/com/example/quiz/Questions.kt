package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.random.Random

class Questions : AppCompatActivity()
{
    var BASE_URL = "https://opentdb.com/"

    lateinit var question: TextView
    lateinit var option_1: TextView
    lateinit var option_2: TextView
    lateinit var option_3: TextView
    lateinit var option_4: TextView


    lateinit var next_btn: Button


    var res: List<Quiz>? = null
    var position: Int = -1

    var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        question = findViewById(R.id.question) as TextView
        option_1 = findViewById(R.id.option_1) as TextView
        option_2 = findViewById(R.id.option_2) as TextView
        option_3 = findViewById(R.id.option_3) as TextView
        option_4 = findViewById(R.id.option_4) as TextView


        next_btn = findViewById(R.id.next)
        next_btn.isEnabled = false


        val i = intent
        val difficulty = i.getStringExtra("difficulty")!!
        val category = i.getIntExtra("category", 0)

        getQuizData(category , difficulty)

    }

    fun getQuizData(category: Int, difficulty: String)
    {
        val serviceBuilder = Retrofit.Builder()
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(BASE_URL)
                            .build()
                            .create(Application::class.java)


        val requestCall = serviceBuilder.getQuizData(category, difficulty)


        requestCall.enqueue(object: Callback<QuizAPIResponse>
        {
            override fun onResponse(call: Call<QuizAPIResponse>, response: Response<QuizAPIResponse>)
            {
                if(response.body() != null)
                {
                    res = response.body()!!.results
                    next(next_btn)
                }
                else
                {
                    Toast.makeText(this@Questions, "Something went wrong. Please try again", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@Questions, MainActivity::class.java))
                }
            }

            override fun onFailure(call: Call<QuizAPIResponse>, t: Throwable)
            {
                Toast.makeText(this@Questions, "Error : " + t.message, Toast.LENGTH_LONG).show()
            }

        })
    }

    @Suppress("DEPRECATION")
    fun next(v: View?)
    {
        if(position != -1)
        {
            val radio_group = findViewById<RadioGroup>(R.id.radio_group)
            if(radio_group.checkedRadioButtonId == -1)
            {
                Toast.makeText(this, "You have to select one of the options ", Toast.LENGTH_LONG).show()
                return
            }

            val btn_id = radio_group!!.checkedRadioButtonId
            val btn = findViewById<RadioButton>(btn_id)
            if(btn.text.toString() == res!![position].correct_answer)
                score++

            findViewById<RadioGroup>(R.id.radio_group).clearCheck()

            if(position == 9)
            {
                var i = Intent(this, Result::class.java)
                i.putExtra("score", score)

                startActivity(i)
            }
        }


        position++

        question.text = Html.fromHtml(res!![position].question)

        var options = listOf(   Html.fromHtml( res!![position].correct_answer ),
                                Html.fromHtml( res!![position].incorrect_answers[0] ),
                                Html.fromHtml( res!![position].incorrect_answers[1] ),
                                Html.fromHtml( res!![position].incorrect_answers[2] ) )

        Collections.shuffle(options)

        option_1.text = options[0]
        option_2.text = options[1]
        option_3.text = options[2]
        option_4.text = options[3]

        if(position == 0)
            next_btn.isEnabled = true
    }
}