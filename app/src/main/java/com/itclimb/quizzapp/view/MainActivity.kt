package com.itclimb.quizzapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.itclimb.quizzapp.R
import com.itclimb.quizzapp.databinding.ActivityMainBinding
import com.itclimb.quizzapp.model.Question
import com.itclimb.quizzapp.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var quizViewModel : QuizViewModel
    lateinit var questionsList: List<Question>

    //TODO
    companion object {
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //Reset the score
        result = 0
        totalQuestions = 0

        //get response from Retrofit
        quizViewModel = ViewModelProvider(this)
            .get(QuizViewModel::class.java)

        //display 1-st questions

        //TODO
        GlobalScope.launch(Dispatchers.Main) {
            quizViewModel.getQuestionsFromLiveData().observe(this@MainActivity, Observer {
                if (it.size>0){
                    questionsList = it
                    Log.i("TAG", "This is the 1st question: ${questionsList[0]}")
                    binding.apply {
                        //TODO
                        txtQuestion.text = questionsList!![0].question
                        radio1.text = questionsList!![0].option1
                        radio2.text = questionsList!![0].option2
                        radio3.text = questionsList!![0].option3
                        radio4.text = questionsList!![0].option4
                    }
                }
            })
        }

        //next btn
        var i = 1
        binding.apply {
            btnNext.setOnClickListener(View.OnClickListener {
                 val selectedOption = radioGroup?.checkedRadioButtonId
                if (selectedOption!=-1) {
                    //TODO
                    val radBtn = findViewById<View>(selectedOption!!) as RadioButton
                    questionsList.let {
                        if (i<it.size) {
                            totalQuestions = it.size;

                            //check if correct
                            if (radBtn.text.toString() == (it[i - 1].correct_option)) {
                                result++;
                                txtResult.text = "Correct answer : $result"
                            }

                            txtQuestion.text = "Question ${i + 1}: " + questionsList[i].question
                            radio1.text = it[i].option1
                            radio2.text = it[i].option2
                            radio3.text = it[i].option3
                            radio4.text = it[i].option4

                            //check if the last question
                            //TODO
                            if (i == it.size!!.minus(1)) {
                                btnNext.text = "FINISH"
                            }

                            radioGroup?.clearCheck()
                            i++
                        }else{
                            if (radBtn.text.toString().equals(it[i-1].correct_option)){
                                 result++;
                                txtResult?.text = "Correct Answer : $result"
                            }else{

                            }

                            val intent = Intent(this@MainActivity,  ResultActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }

                }else{
                     Toast.makeText( this@MainActivity, "Please select One option", Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}