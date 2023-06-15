package com.andrirahmadani.listcode

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CodeAdapter(val data: List<CodeModel>) : RecyclerView.Adapter<CodeAdapter.MyViewHolder>() {

    class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {
        val textView1 = row.findViewById<TextView>(R.id.nama)
        val textView2 = row.findViewById<TextView>(R.id.nama_ilmiah)
        val textView3 = row.findViewById<TextView>(R.id.nama_filum)
        val imageView = row.findViewById<ImageView>(R.id.image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.hewan_item,
            parent, false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView1.text = data.get(position).nama
        holder.textView2.text = data.get(position).called
        holder.textView3.text = data.get(position).jenis
        holder.imageView.setImageResource(data.get(position).gambar)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val model = data.get(position)
            val gambar: Int = model.gambar
            val nama : String = model.nama
            val namaIlmiah: String = model.called
            val deskrpsi = model.deskripsi
            val filum = model.jenis
            val habitat = model.ranah
            val spesies = model.kepanjangan

            //Membuat Intent

            val intent = Intent(context, DetailCodeActivity::class.java)
            //Putextra
            intent.putExtra("Nama", nama)
            intent.putExtra("Ilmiah", namaIlmiah)
            intent.putExtra("Gambar" , gambar)
            intent.putExtra("called", deskrpsi)
            intent.putExtra("ranah", filum)
            intent.putExtra("jenis", habitat)
            intent.putExtra("ranah", spesies)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = data.size
}