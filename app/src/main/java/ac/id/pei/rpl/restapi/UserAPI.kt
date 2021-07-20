package ac.id.pei.rpl.restapi

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("User")
    fun user(
            @Body userRequest: UserRequest
    ): Call<UserResponse>
}