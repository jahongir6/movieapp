package com.example.movieapp.models

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.RvItemBinding

class RvAdapter(val list:ArrayList<Movie>, val context: Context, val rvAction: RvAction):
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(val rvItem: RvItemBinding): RecyclerView.ViewHolder(rvItem.root){
        fun onBind(movie:Movie, position: Int){

            val animation= AnimationUtils.loadAnimation(context, R.anim.rv_anim)
            rvItem.root.startAnimation(animation)

            rvItem.name.text=movie.name
            rvItem.root.setOnClickListener {
                rvAction.click(movie, position)
            }

            rvItem.edit.setOnClickListener {
                rvAction.edtClick(movie, position)
            }

            rvItem.delete.setOnClickListener {
                list.remove(movie)
                this@RvAdapter.notifyItemRemoved(position)
                MySharedPrefarance.init(context)
                MySharedPrefarance.obektString=list
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface RvAction{
        fun click(movie: Movie, position: Int)
        fun edtClick(movie: Movie, position: Int)
    }

}