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

class PinjamAdapter(private val listku: ArrayList<KontakData>): RecyclerView.Adapter<PinjamAdapter.KontakViewHolder>(){

    inner class KontakViewHolder(viewku: View): RecyclerView.ViewHolder(viewku) {
        var tvId: TextView = viewku.findViewById(R.id.tv_idpinjam)
        var tvTglP: TextView = viewku.findViewById(R.id.tv_tglpinjam)
        var tvNim: TextView = viewku.findViewById(R.id.tv_nimm)
        var tvJudul: TextView = viewku.findViewById(R.id.tv_judulbuku)
        var tvTglK: TextView = viewku.findViewById(R.id.tv_tglkembali)
        var btnDelte: ImageButton = viewku.findViewById(R.id.btn_data_del)
        var btnUpdate: ImageButton = viewku.findViewById(R.id.btn_data_edit)
        var apiIterface: ServiceInterfacePinjam? = Repository.getDataAPI().create(ServiceInterfacePinjam::class.java)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakViewHolder {
        val viewView: View = LayoutInflater.from(parent.context).inflate(R.layout.data_pinjam, parent, false)
        return KontakViewHolder(viewView)
    }

    override fun onBindViewHolder(holder: KontakViewHolder, position: Int) {
        val dataku = listku[position]
        holder.tvId.text = dataku.id_pinjam.toString()
        holder.tvTglP.text = dataku.tgl_pinjam
        holder.tvNim.text = dataku.nim.toString()
        holder.tvJudul.text = dataku.judul_buku
        holder.tvTglK.text = dataku.tgl_kembali
        holder.btnUpdate.setOnClickListener {
            val bundle= Bundle()
            val pindah = Intent(holder.itemView.context, UpdatePinjam::class.java)
            bundle.putString("id_pinjam", dataku.id_pinjam.toString())
            bundle.putString("tgl_pinjam", dataku.tgl_pinjam.toString())
            bundle.putString("nim", dataku.nim .toString())
            bundle.putString("judul_buku", dataku.judul_buku.toString())
            bundle.putString("tgl_kembali", dataku.tgl_kembali.toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
        holder.btnDelte.setOnClickListener {
            holder.apiIterface!!.deletePinjam(dataku.id_pinjam!!).enqueue(object : Callback<KontakData>{
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
