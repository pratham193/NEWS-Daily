package com.example.news

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter2( data:ArrayList<AdapterClass>,var context: Context):
    RecyclerView.Adapter<Adapter2.viewholder>() {




    var data:ArrayList<AdapterClass>
    init {
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        var layout: View = LayoutInflater.from(context).inflate(R.layout.item2, parent, false)


        return viewholder(layout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder:viewholder, position: Int) {


        val prif=PreferenceClass(context)



        val itemdata=data[position]
        holder.name.text=itemdata.text


        holder.name.setOnClickListener{
            MainActivity.url= itemdata.web

            prif.setcount(prif.getcount()+1)

            ContextCompat.startActivity(context, Intent(context, OnClick::class.java), null)
        }
    }
    class viewholder(itemView: View): RecyclerView.ViewHolder(itemView){


        internal var name: TextView


        init {

            name=itemView.findViewById(R.id.name)

        }


    }




}




