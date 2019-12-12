package id.muhadif.kade_sub_1.feature.leagues.list


import com.bumptech.glide.Glide
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import id.muhadif.kade_sub_1.data.local.League
import id.muhadif.kade_sub_1.R
import kotlinx.android.synthetic.main.item_league.*
import retrofit2.http.Url
import android.os.Environment.getExternalStorageDirectory
import android.os.Environment
import java.io.File


class LeagueItem(val league: League) : Item() {
    override fun getLayout(): Int = R.layout.item_league

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.tv_league.text = league.name

        Glide.with(viewHolder.root)
            .load(league.logo)
            .into(viewHolder.iv_league)
    }


}