package ac.id.pei.rpl.restapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("data")
    @Expose
    var data: User? = null

    class  User {
        @SerializedName("username")
        @Expose
        var username: String? = null

        @SerializedName("password")
        @Expose
        var password: String? = null

    }
}