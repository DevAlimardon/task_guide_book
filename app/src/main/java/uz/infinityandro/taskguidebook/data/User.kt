package uz.infinityandro.taskguidebook.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @field:SerializedName("venue")
    val venue: String? = null,

    @field:SerializedName("endDate")
    val endDate: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("loginRequired")
    val loginRequired: Boolean? = null,

    @field:SerializedName("objType")
    val objType: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("startDate")
    val startDate: String? = null
)
