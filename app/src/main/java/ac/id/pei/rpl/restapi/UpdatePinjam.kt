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

class UpdatePinjam : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etNim: EditText
    lateinit var etTglp: EditText
    lateinit var etJudul: EditText
    lateinit var etTglk: EditText
    lateinit var valTglp: String
    lateinit var valNim: String
    lateinit var valJudul: String
    lateinit var valTglk: String
    lateinit var valIdp: String
    lateinit var apiService: ServiceInterfacePinjam
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_pinjam)
        getMyData()
        declaration()
        myfunction()
    }
    fun declaration() {
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etTglp = findViewById(R.id.et_up_tglp)
        etNim = findViewById(R.id.et_up_nimm)
        etJudul = findViewById(R.id.et_up_judulb)
        etTglk = findViewById(R.id.et_up_tglk)
        apiService = Repository.getDataAPI().create(ServiceInterfacePinjam::class.java)
    }

    fun getMyData() {
        val myValue = intent.extras
        if (myValue != null) {
            valTglp = myValue.getString("tgl_pinjam").toString()
            valNim = myValue.getString("nim").toString()
            valJudul = myValue.getString("judul_buku").toString()
            valTglk = myValue.getString("tgl_kembali").toString()
            valIdp = myValue.getString("id_pinjam").toString()
        }
    }

    fun myfunction() {
        etTglp.setText(valTglp)
        etNim.setText(valNim)
        etJudul.setText(valJudul)
        etTglk.setText(valTglk)
        val pindah = Intent(this, Pinjam::class.java)
        btnSubmit.setOnClickListener {
            apiService.updatePinjam(
                valIdp.toInt(),
                etTglp.text.toString(),
                etNim.text.toString(),
                etJudul.text.toString(),
                etTglk.text.toString()
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