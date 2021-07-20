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

class UpdateMhs : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etNUmber: EditText
    lateinit var etJumlah: EditText
    lateinit var valNama: String
    lateinit var valJurusan: String
    lateinit var valTlp: String
    lateinit var valNIm: String
    lateinit var apiService: ServiceInterfaceMhs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_mhs)
        getMyData()
        declaration()
        myfunction()
    }

    fun declaration() {
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etName = findViewById(R.id.et_up_nama)
        etNUmber = findViewById(R.id.et_up_jurusan)
        etJumlah = findViewById(R.id.et_up_tlp)
        apiService = Repository.getDataAPI().create(ServiceInterfaceMhs::class.java)
    }

    fun getMyData() {
        val myValue = intent.extras
        if (myValue != null) {
            valNama = myValue.getString("nama").toString()
            valJurusan = myValue.getString("jurusan").toString()
            valTlp = myValue.getString("tlp").toString()
            valNIm = myValue.getString("nim").toString()
        }
    }

    fun myfunction() {
        etName.setText(valNama)
        etNUmber.setText(valJurusan)
        etJumlah.setText(valTlp)
        val pindah = Intent(this, Mahasiswa::class.java)
        btnSubmit.setOnClickListener {
            apiService.updateMahasiswa(
                valNIm.toInt(),
                etName.text.toString(),
                etNUmber.text.toString(),
                etJumlah.text.toString()
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