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

class AddMhs : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etId: EditText
    lateinit var etNumber: EditText
    lateinit var etJumlah: EditText
    lateinit var apiService: ServiceInterfaceMhs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mhs)
        declaration()
        myfunction()
    }

    fun declaration() {
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etName = findViewById(R.id.et_add_nama)
        etNumber = findViewById(R.id.et_add_jurusan)
        etJumlah = findViewById(R.id.et_add_tlp)
        apiService = Repository.getDataAPI().create(ServiceInterfaceMhs::class.java)
    }

    fun myfunction() {
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            val array = KontakData()
            array.nama = etName.text.toString()
            array.jurusan = etNumber.text.toString()
            array.tlp = etJumlah.text.toString()
            apiService.postMahasiswa(array).enqueue(object : Callback<KontakData> {
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    startActivity(Intent(this@AddMhs, Mahasiswa::class.java))
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