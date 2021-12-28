package uz.infinityandro.taskguidebook.data.api

import retrofit2.Response
import retrofit2.http.GET
import uz.infinityandro.taskguidebook.data.UserNetwork

interface ApiService {
    @GET("v2/upcomingGuides")
    suspend fun getAllData():Response<UserNetwork>
}