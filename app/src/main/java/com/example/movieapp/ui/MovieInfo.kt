package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieInfoBinding
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.MySharedPrefarance

class MovieInfo : AppCompatActivity() {
    private lateinit var binding: ActivityMovieInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.apply {

            MySharedPrefarance.init(this@MovieInfo)
            MovieList.movieList=MySharedPrefarance.obektString

            val position=intent.getIntExtra("position", 0)

            name.text="${name.text}: ${MovieList.movieList[position].name}"
            authors.text="${authors.text}: ${MovieList.movieList[position].authors}"
            about.text="${about.text}: ${MovieList.movieList[position].about}"
            date.text="${date.text}: ${MovieList.movieList[position].date}"

            close.setOnClickListener {
                finish()
            }

        }

    }
}