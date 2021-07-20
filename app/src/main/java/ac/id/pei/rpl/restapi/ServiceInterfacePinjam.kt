package ac.id.pei.rpl.restapi

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterfacePinjam {
    @GET("Peminjaman")
    fun getData(): Call<List<KontakData>>
    @POST("Peminjaman")
    fun postPeminjaman(@Body kontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Peminjaman", hasBody = true)
    fun deletePinjam(@Field("id_pinjam") id_pinjam: Int): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Peminjaman", hasBody = true)
    fun updatePinjam(
        @Field("id_pinjam") id_pinjam: Int,
        @Field("tgl_pinjam") tgl_pinjam: String,
        @Field("nim") nim: String,
        @Field("judul_buku") judul_buku: String,
        @Field("tgl_kembali") tgl_kembali: String
    ): Call<KontakData>
}