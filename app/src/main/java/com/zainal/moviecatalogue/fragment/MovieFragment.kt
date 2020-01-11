package com.zainal.moviecatalogue.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import com.zainal.moviecatalogue.DetailActivity
import com.zainal.moviecatalogue.R
import com.zainal.moviecatalogue.adapter.MovieAdapter
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private val list = ArrayList<Movie>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movie.setHasFixedSize(true)
        list.addAll(getMovies())
        showMovie()
    }

    @SuppressLint("Recycle")
    fun getMovies() : ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.movie_title)
        val dataOverview = resources.getStringArray(R.array.movie_overview)
        val dataPoster = resources.obtainTypedArray(R.array.movie_poster)
        val dataRelease = resources.getStringArray(R.array.movie_release)

        val listMovie = ArrayList<Movie>()
        for (position in dataTitle.indices) {
            val movie = Movie(
                dataPoster.getResourceId(position, -1),
                dataTitle[position],
                dataOverview[position],
                dataRelease[position]
            )
            listMovie.add(movie)
        }
        return listMovie
    }

    private fun showMovie() {
        rv_movie.layoutManager = GridLayoutManager(context, 2)
        val movieAdapter = MovieAdapter(list)
        rv_movie.adapter = movieAdapter

        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val detailMovie = Intent(activity, DetailActivity::class.java)
                detailMovie.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(detailMovie)
            }
        })
    }
}
