package id.muhadif.kade_sub_1.feature.leagues.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import id.muhadif.kade_sub_1.R
import id.muhadif.kade_sub_1.data.local.League
import kotlinx.android.synthetic.main.activity_detail_league.*
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar


class DetailLeagueActivity : AppCompatActivity() {



    companion object {
        val LEAGUE = "league"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()

        setupActionBar()

        intent.getParcelableExtra<League>(LEAGUE)?.let {
            showLeague(it)
        }


    }

    private fun initUi() {
        scrollView {
            bottomPadding = dip(8)

            linearLayout {

                gravity = Gravity.CENTER
                orientation = LinearLayout.VERTICAL
                imageView {
                    id = R.id.iv_league_detail
                }.lparams(width = dip(200), height = dip(200)) {
                    topMargin = dip(16)
                }
                textView {
                    id = R.id.tv_league_detail
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textColor = Color.BLACK
                    textSize = 20f //sp
                }.lparams(width = matchParent) {
                    marginStart = dip(16)
                    leftMargin = dip(16)
                    topMargin = dip(8)
                    marginEnd = dip(16)
                    rightMargin = dip(16)
                }
                textView {
                    id = R.id.tv_league_desc
                }.lparams(width = matchParent) {
                    marginStart = dip(16)
                    leftMargin = dip(16)
                    topMargin = dip(8)
                    marginEnd = dip(16)
                    rightMargin = dip(16)
                }
            }.lparams(width = matchParent, height = matchParent)
        }
    }

    private fun setupActionBar() {
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem)
        = when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    private fun showLeague(league: League) {
        league?.let {
            tv_league_detail.text = it.name
            tv_league_desc.text = it.desc
            Glide.with(applicationContext)
                .load(it.logo)
                .into(iv_league_detail)
        }
    }
}
