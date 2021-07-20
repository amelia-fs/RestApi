package ac.id.pei.rpl.restapi

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterfaceKembali {
    @GET("Pengembalian")
    fun getData(): Call<List<KontakData>>
    @POST("Pengembalian")
    fun postKembali(@Body kontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Pengembalian", hasBody = true)
    fun deleteKembali(@Field("id_kembali") id_pinjam: Int): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Pengembalian", hasBody = true)
    fun updateKembali(
        @Field("id_kembali") id_kembali: Int,
        @Field("tgl_kembali") tgl_kembali: String,
        @Field("nim") nim: String,
        @Field("judul_buku") judul_buku: String
    ): Call<KontakData>
}