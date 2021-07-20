package ac.id.pei.rpl.restapi

import ac.id.pei.rpl.restapi.KontakData
import ac.id.pei.rpl.restapi.R
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MahasiswaAdapter(private val listku: ArrayList<KontakData>): RecyclerView.Adapter<MahasiswaAdapter.KontakViewHolder>(){

    inner class KontakViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvNim: TextView = viewku.findViewById(R.id.tv_nim)
        var tvNama: TextView = viewku.findViewById(R.id.tv_nama)
        var tvJurusan: TextView = viewku.findViewById(R.id.tv_jurusan)
        var tvTlp: TextView = viewku.findViewById(R.id.tv_tlp)
        var btnDelte: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: ImageButton = viewku.findViewById(R.id.btn_data_edit)
        var apiIterface: ServiceInterfaceMhs? = Repository.getDataAPI().create(ServiceInterfaceMhs::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_mhs, parent, false)
        return KontakViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: KontakViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvNim.text = dataku.nim.toString()
        holder.tvNama.text = dataku.nama
        holder.tvJurusan.text = dataku.jurusan
        holder.tvTlp.text = dataku.tlp
        holder.btnUpdate.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, UpdateMhs::class.java)
            bundle.putString("nim", dataku.nim.toString())
            bundle.putString("nama", dataku.nama.toString())
            bundle.putString("jurusan", dataku.jurusan.toString())
            bundle.putString("tlp", dataku.tlp.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
        holder.btnDelte.setOnClickListener {
            holder.apiIterface!!.deleteMahasiswa(dataku.nim!!).enqueue(object : Callback<KontakData>{
                override fun onResponse(call: Call<KontakData>, response: Response<KontakData>) {
                    if (response.isSuccessful){
                        listku.removeAt(position)
                        notifyDataSetChanged()
                        Toast.makeText(holder.itemView.context, "Delete Data Success", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<KontakData>, t: Throwable) {
                    Toast.makeText(holder.itemView.context, "Delete Data Failed", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
    override fun getItemCount(): Int {
        return listku.size
    }
}
