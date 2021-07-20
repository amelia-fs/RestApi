package ac.id.pei.rpl.restapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MenuUtama : AppCompatActivity() {
    lateinit var buku : ImageView
    lateinit var mhs : ImageView
    lateinit var pinjam : ImageView
    lateinit var kembali : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_utama)
        buku = findViewById(R.id.databuku)
        mhs = findViewById(R.id.datamhs)
        pinjam = findViewById(R.id.dataloning)
        kembali = findViewById(R.id.datakembali)
        buku.setOnClickListener {
            val pindah = Intent(this, MainActivity::class.java)
            startActivity(pindah)
        }
        mhs.setOnClickListener {
            val pindah = Intent(this, Mahasiswa::class.java)
            startActivity(pindah)
        }
        pinjam.setOnClickListener {
            val pindah = Intent(this, Pinjam::class.java)
            startActivity(pindah)
        }
        kembali.setOnClickListener {
            val pindah = Intent(this, Kembali::class.java)
            startActivity(pindah)
        }
    }
}