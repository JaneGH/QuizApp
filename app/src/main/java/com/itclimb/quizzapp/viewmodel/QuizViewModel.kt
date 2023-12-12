package com.itclimb.quizzapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.itclimb.quizzapp.model.QuestionsList
import com.itclimb.quizzapp.repository.QuizRepository

class QuizViewModel :ViewModel()  {
    var repository : QuizRepository = QuizRepository()
    //TODO
    lateinit var questionsLiveData: LiveData<QuestionsList>

    //TODO
    init{
        questionsLiveData = repository.getQuestionsFromAPI();
    }

    fun getQuestionsFromLiveData():LiveData<QuestionsList> {
        return questionsLiveData
    }

}