package ac.id.pei.rpl.restapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class Pinjam : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterfacePinjam
    private var ambilData: ArrayList<KontakData> = arrayListOf()
    lateinit var btnadd: View
    lateinit var btnex: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pinjam)
        rvdata = findViewById(R.id.rv_datapinjam)
        btnadd = findViewById(R.id.btn_main_add)
        btnex = findViewById(R.id.btn_main_ex)
        btnadd.setOnClickListener {
            startActivity(Intent(this, AddPinjam::class.java))
        }
        apiService = Repository.getDataAPI().create(ServiceInterfacePinjam::class.java)
        apiService.getData().enqueue(object : Callback<List<KontakData>> {
            override fun onResponse(
                call: retrofit2.Call<List<KontakData>>,
                response: Response<List<KontakData>>
            ) {
                if (response.isSuccessful){
                    val res = response.body()
                    ambilData.addAll(res!!)
                    rvdata.layoutManager = LinearLayoutManager(this@Pinjam)
                    rvdata.adapter = PinjamAdapter(ambilData)
                }
            }
            override fun onFailure(call: retrofit2.Call<List<KontakData>>, t: Throwable) {
            }
        })
        btnex.setOnClickListener {
            startActivity(Intent(this, MenuUtama::class.java))
        }
    }
}