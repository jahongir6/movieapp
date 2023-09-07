package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityAddMovieBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.MySharedPrefarance

class AddMovie : AppCompatActivity() {
    private lateinit var binding: ActivityAddMovieBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.apply {

            MySharedPrefarance.init(this@AddMovie)

            MovieList.movieList=MySharedPrefarance.obektString

            val name=name.text
            val authors=authors.text
            val about=about.text
            val date=date.text


            btn.setOnClickListener {
                MovieList.movieList.add(Movie("$name", "$authors", "$about", "$date"))
                MySharedPrefarance.obektString= MovieList.movieList
                finish()
            }

        }
    }
}