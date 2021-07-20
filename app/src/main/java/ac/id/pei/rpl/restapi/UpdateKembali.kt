package ac.id.pei.rpl.restapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateKembali : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etNim: EditText
    lateinit var etJudul: EditText
    lateinit var etTglk: EditText
    lateinit var valNim: String
    lateinit var valJudul: String
    lateinit var valTglk: String
    lateinit var valIdk: String
    lateinit var apiService: ServiceInterfaceKembali
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_kembali)
        getMyData()
        declaration()
        myfunction()
    }
    fun declaration() {
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etTglk = findViewById(R.id.et_up_tglke)
        etNim = findViewById(R.id.et_up_nimmm)
        etJudul = findViewById(R.id.et_up_judulbu)
        apiService = Repository.getDataAPI().create(ServiceInterfaceKembali::class.java)
    }

    fun getMyData() {
        val myValue = intent.extras
        if (myValue != null) {
            valTglk = myValue.getString("tgl_kembali").toString()
            valNim = myValue.getString("nim").toString()
            valJudul = myValue.getString("judul_buku").toString()
            valIdk = myValue.getString("id_kembali").toString()
        }
    }

    fun myfunction() {
        etTglk.setText(valTglk)
        etNim.setText(valNim)
        etJudul.setText(valJudul)
        val pindah = Intent(this, Kembali::class.java)
        btnSubmit.setOnClickListener {
            apiService.updateKembali(
                valIdk.toInt(),
                etTglk.text.toString(),
                etNim.text.toString(),
                etJudul.text.toString()
            ).enqueue(object :
                Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(pindah)
                    Toast.makeText(baseContext, "Update Data Success", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(baseContext, "Update Data Failed", Toast.LENGTH_SHORT).show()
                }

            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}