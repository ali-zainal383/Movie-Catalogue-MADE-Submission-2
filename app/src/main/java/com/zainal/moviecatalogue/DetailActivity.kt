package com.zainal.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        val poster = data.imageSrc
        val title = data.name
        val overview = data.description
        val release = data.release

        iv_poster_detail.setImageResource(poster)
        tv_movie_title.text = title
        tv_overview_detail.text = overview
        tv_release_detail.text = release
    }
}
