package com.itclimb.quizzapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.itclimb.quizzapp.R
import com.itclimb.quizzapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding:ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        binding.txtAnswer.text = "Your score is: " + MainActivity.result + "/" + MainActivity.totalQuestions
        binding.btnBack.setOnClickListener(){
            var intent = Intent(this@ResultActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}