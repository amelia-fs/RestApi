package ac.id.pei.rpl.restapi

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
                .baseUrl("https://sudoowl.site/api/mahasiswa_2018/index.php/api/UAS2021_20180402/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

    }
}