package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity()
{
    var category : Int = 0
    var difficulty: String = "easy"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var categories_value: HashMap<String, Int> = HashMap<String, Int>()

        categories_value["Any"] = 0
        categories_value["General Knowledge"] = 9
        categories_value["Entertainment: Books"] = 10
        categories_value["Entertainment: Film"] = 11
        categories_value["Entertainment: Music"] = 12
        categories_value["Entertainment: Musicals & Theatres"] = 13
        categories_value["Entertainment: Television"] = 14
        categories_value["Entertainment: Video Games"] = 15
        categories_value["Entertainment: Board Games"] = 16
        categories_value["Science & Nature"] = 17
        categories_value["Science: Computers"] = 18
        categories_value["Science: Mathematics"] = 19
        categories_value["Mythology"] = 20
        categories_value["Sports"] = 21
        categories_value["Geography"] = 22
        categories_value["History"] = 23
        categories_value["Politics"] = 24
        categories_value["Art"] = 25
        categories_value["Celebrities"] = 26
        categories_value["Animals"] = 27
        categories_value["Vehicles"] = 28
        categories_value["Entertainment: Comics"] = 29
        categories_value["Science: Gadgets"] = 30
        categories_value["Entertainment: Japanese Anime & Manga"] = 31
        categories_value["Entertainment: Cartoon & Animations"] = 32
        categories_value["Vehicles"] = 28

        // Categories
        var categories = arrayOf<String>()
        categories_value.forEach{
            categories += it.key
        }
        var category_spinner = findViewById<Spinner>(R.id.category)
        val category_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        category_spinner.adapter = category_adapter

        category_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long)
            {
                category = categories_value.get(categories[position].toString())!!
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                category = 0
            }

        }
        //Categories


        var difficulty_values = arrayOf("easy", "medium", "hard")
        // difficulty
        var difficulty_spinner = findViewById<Spinner>(R.id.difficulty)
        val difficulty_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, difficulty_values)
        difficulty_spinner.adapter = difficulty_adapter

        difficulty_spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long)
            {
                difficulty = difficulty_values[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                difficulty = "easy"
            }

        }
        //difficulty
    }

    fun start(v: View?)
    {
        var i = Intent(this, Questions::class.java)
        i.putExtra("category", category)
        i.putExtra("difficulty", difficulty)

        startActivity(i)
    }

}