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

class AddPinjam : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etIdp: EditText
    lateinit var etTglp: EditText
    lateinit var etNim: EditText
    lateinit var etJudul: EditText
    lateinit var etTglk: EditText
    lateinit var apiService: ServiceInterfacePinjam
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pinjam)
        declaration()
        myfunction()
    }
    fun declaration() {
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etTglp = findViewById(R.id.et_add_tglp)
        etNim = findViewById(R.id.et_add_nimm)
        etJudul = findViewById(R.id.et_add_judulb)
        etTglk = findViewById(R.id.et_add_tglk)
        apiService = Repository.getDataAPI().create(ServiceInterfacePinjam::class.java)
    }

    fun myfunction() {
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = KontakData()
            array.tgl_pinjam = etTglp.text.toString()
            array.nim = etNim.text.toString().toInt()
            array.judul_buku = etJudul.text.toString()
            array.tgl_kembali = etTglk.text.toString()
            apiService.postPeminjaman(array).enqueue(object : Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(Intent(this@AddPinjam, Pinjam::class.java))
                    Toast.makeText(baseContext, "Add Data Success", Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(baseContext, "Add Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}