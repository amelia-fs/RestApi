package ac.id.pei.rpl.restapi
import retrofit2.Call
import retrofit2.http.*

interface ServiceInterface {
    @GET("Buku")
    fun getData(): Call<List<KontakData>>
    @POST("Buku")
    fun postKontak(@Body kontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Buku", hasBody = true)
    fun deleteKontak(@Field("id") id: Int): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Buku", hasBody = true)
    fun updateKontak(
        @Field("id") id: Int,
        @Field("judul") barang: String,
        @Field("kategori") harga: String,
        @Field("stok") jumlah: String
    ): Call<KontakData>
}


