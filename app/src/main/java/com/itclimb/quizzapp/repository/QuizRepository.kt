package com.itclimb.quizzapp.repository

import QuestionsAPI
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.itclimb.quizzapp.model.QuestionsList
import com.itclimb.quizzapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {
    var questionsAPI:QuestionsAPI
    init {
        questionsAPI = RetrofitInstance().getRetrofitInstance().create(QuestionsAPI::class.java)

    }
        fun getQuestionsFromAPI():LiveData<QuestionsList> {
            var data = MutableLiveData<QuestionsList>()
            var questionsList : QuestionsList
            GlobalScope.launch(Dispatchers.IO ) {
                //returning the Response<QuestionsList>
                val response = questionsAPI
                    .getQuestions()
                if (response!=null) {
                    //saving data to array list
                    questionsList = response.body()!!
                    data.postValue(questionsList)
                    Log.i("TAGY",  ""+data.value )

                }
            }
            return data
        }
    }
