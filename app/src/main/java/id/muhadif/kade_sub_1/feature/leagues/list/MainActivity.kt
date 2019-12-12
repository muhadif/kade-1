package id.muhadif.kade_sub_1.feature.leagues.list

import android.content.Intent
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import id.muhadif.kade_sub_1.R
import id.muhadif.kade_sub_1.data.local.League
import id.muhadif.kade_sub_1.feature.leagues.detail.DetailLeagueActivity
import id.muhadif.kade_sub_1.feature.leagues.detail.DetailLeagueActivity.Companion.LEAGUE
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView


class MainActivity : AppCompatActivity() {

    private val log = AnkoLogger(this.javaClass)
    private var leagues : MutableList<League> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initUi()

        fetchLeagues()

        initRecyclerView(leagues.toLeagueItem())

    }

    private fun initUi() {
        linearLayout {
            recyclerView {
                id = R.id.rv_leagues
                horizontalPadding = dip(16)
            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun initRecyclerView(leagueItems :List<LeagueItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(leagueItems)
        }

        rv_leagues.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as LeagueItem).let {
                startActivity<DetailLeagueActivity>(
                    LEAGUE to item.league
                )

            }
        }

    }

    private fun List<League>.toLeagueItem() : List<LeagueItem> {
        return this.map {
            LeagueItem(it)
        }
    }


    private fun fetchLeagues(){
        val res : Resources = resources
        val nameLeague = res.getStringArray(R.array.name_league)
        val idLeague = res.getStringArray(R.array.id_league)
        val descLeague = res.getStringArray(R.array.desc_league)
        val logoLeague = res.getStringArray(R.array.logo)

        nameLeague.forEachIndexed{index, value ->
            leagues.add(
                League(
                    idLeague[index].toString(),
                    value.toString(),
                    descLeague[index].toString(),
                    res.getIdentifier("${logoLeague[index]}", "drawable", packageName)
                )
            )
        }



    }

}
