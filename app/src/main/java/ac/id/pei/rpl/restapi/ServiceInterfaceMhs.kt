package ac.id.pei.rpl.restapi

import retrofit2.Call
import retrofit2.http.*

interface ServiceInterfaceMhs {
    @GET("Mahasiswa")
    fun getData(): Call<List<KontakData>>
    @POST("Mahasiswa")
    fun postMahasiswa(@Body kontakData: KontakData): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Mahasiswa", hasBody = true)
    fun deleteMahasiswa(@Field("nim") nim: Int): Call<KontakData>

    @FormUrlEncoded
    @HTTP(method="PUT", path="Mahasiswa", hasBody = true)
    fun updateMahasiswa(
        @Field("nim") id: Int,
        @Field("nama") barang: String,
        @Field("jurusan") harga: String,
        @Field("tlp") jumlah: String
    ): Call<KontakData>
}