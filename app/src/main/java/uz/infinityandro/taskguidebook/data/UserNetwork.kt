package uz.infinityandro.taskguidebook.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserNetwork(

	@field:SerializedName("total")
	val total: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem>? = null
)
data class DataItem(

	@field:SerializedName("venue")
	val venue: Venue? = null,

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
):Serializable
data class Venue(
	val any: Any? = null
):Serializable
