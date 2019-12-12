package id.muhadif.kade_sub_1.data.local

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: String,
    val name: String,
    val desc : String,
    val logo : Int
) : Parcelable