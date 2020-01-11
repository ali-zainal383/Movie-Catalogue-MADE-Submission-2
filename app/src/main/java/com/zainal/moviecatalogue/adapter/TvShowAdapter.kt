package com.zainal.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zainal.moviecatalogue.R
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.android.synthetic.main.item_tv_show.view.*

class TvShowAdapter(private val listTvShow: ArrayList<Movie>) : RecyclerView.Adapter<TvShowAdapter.GridViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GridViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tv_show, viewGroup, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listTvShow[position])
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(movie.imageSrc)
                    .apply(RequestOptions().override(350, 550))
                    .into(iv_tv_show_poster)

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}