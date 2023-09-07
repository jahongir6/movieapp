package com.example.movieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.models.Movie
import com.example.movieapp.models.MovieList
import com.example.movieapp.models.MySharedPrefarance
import com.example.movieapp.models.RvAdapter
import com.example.movieapp.ui.AddMovie
import com.example.movieapp.ui.EditMovie
import com.example.movieapp.ui.MovieInfo

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var rvAdapter: RvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addMovie.setOnClickListener {
            startActivity(Intent(this, AddMovie::class.java))
        }

        MySharedPrefarance.init(this)
        MovieList.movieList= MySharedPrefarance.obektString

    }
    override fun onResume() {
        super.onResume()

        binding.apply {
            rvAdapter= RvAdapter(MovieList.movieList, this@MainActivity, object : RvAdapter.RvAction{

                override fun click(movie: Movie, position:Int) {
                    startActivity(Intent(this@MainActivity, MovieInfo::class.java).putExtra("position", position))
                }

                override fun edtClick(movie: Movie, position: Int) {
                    startActivity(Intent(this@MainActivity, EditMovie::class.java).putExtra("position", position))
                }


            })
            rv.adapter=rvAdapter
        }
        rvAdapter.notifyItemInserted(MovieList.movieList.size-1)
    }
}