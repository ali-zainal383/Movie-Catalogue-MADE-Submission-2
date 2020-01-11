package com.zainal.moviecatalogue.fragment


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.zainal.moviecatalogue.DetailActivity
import com.zainal.moviecatalogue.R
import com.zainal.moviecatalogue.adapter.TvShowAdapter
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    private val list = ArrayList<Movie>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_tv_show.setHasFixedSize(true)
        list.addAll(getTvShow())
        showTv()
    }

    @SuppressLint("Recycle")
    fun getTvShow() : ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.tv_show_title)
        val dataOverview = resources.getStringArray(R.array.tv_show_overview)
        val dataPoster = resources.obtainTypedArray(R.array.tv_show_poster)
        val dataRelease = resources.getStringArray(R.array.tv_show_release)

        val listTvShow = ArrayList<Movie>()
        for (position in dataTitle.indices) {
            val movie = Movie(
                dataPoster.getResourceId(position, -1),
                dataTitle[position],
                dataOverview[position],
                dataRelease[position]
            )
            listTvShow.add(movie)
        }
        return listTvShow
    }

    private fun showTv() {
        rv_tv_show.layoutManager = GridLayoutManager(context, 2)
        val tvShowAdapter = TvShowAdapter(list)
        rv_tv_show.adapter = tvShowAdapter

        tvShowAdapter.setItemClickCallback(object : TvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val detailTvShow = Intent(activity, DetailActivity::class.java)
                detailTvShow.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(detailTvShow)
            }
        })
    }
}
