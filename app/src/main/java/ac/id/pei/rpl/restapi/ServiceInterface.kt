package ac.id.pei.rpl.restapi
import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Kontak")
    fun getData(): Call<List<KontakData>>
}
