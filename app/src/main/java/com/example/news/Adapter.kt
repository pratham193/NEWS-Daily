package com.example.news

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.*
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.alert.*
import org.json.JSONException
import org.json.JSONObject

class Adapter( data:ArrayList<AdapterClass>,var context: Context):
    RecyclerView.Adapter<Adapter.viewholder>() {


   

 var data:ArrayList<AdapterClass>
    init {
        this.data=data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {

        var layout:View = LayoutInflater.from(context).inflate(R.layout.item, parent, false)


        return viewholder(layout)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {


        val prif=PreferenceClass(context)
       
       

     val itemdata=data[position]
        holder.name.text=itemdata.text
        Glide.with(context).load(itemdata.image).into(holder.img)

        holder.card.setOnClickListener{



            if(MainActivity.InternationalRefrence==1){
                val alert= Dialog(context)
            alert.setContentView(R.layout.alert)
            alert.window?.setType(WindowManager.LayoutParams.TYPE_APPLICATION_PANEL)
            alert.setTitle("OPENING...")

            val website=alert.findViewById<Button>(R.id.web)
            val paper=alert.findViewById<Button>(R.id.paper)


            alert.show()


            website.setOnClickListener{
//
                    prif.setcount(prif.getcount()+1)
//
                MainActivity.url= itemdata.web

                startActivity(context,Intent(context,OnClick::class.java),null)

            }
            paper.setOnClickListener {

//
                    prif.setcount(prif.getcount()+1)
//
                startActivity(context,Intent(context,OnClick::class.java),null)
                MainActivity.url= itemdata.paper
            }



        }else{

                if(position==4){
//
                        prif.setcount(prif.getcount()+1)
//
                    MainActivity.url= itemdata.web

                    startActivity(context,Intent(context,OnClick::class.java),null)


                }else{
                    if(prif.getcount()>=3){
                    if (MainActivity.mInterstitialAd.isLoaded) {
                        MainActivity.mInterstitialAd.show()
                        prif.clearCount()


                    } else {
                        prif.setcount(prif.getcount()+1)
                        Toast.makeText(context,"not shown",Toast.LENGTH_SHORT).show()
                        startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/${itemdata.web}")),null)

                    }
                }else{
                        prif.setcount(prif.getcount()+1)
                        startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/${itemdata.web}")),null)

                    }

                   MainActivity.mInterstitialAd.adListener = object:AdListener() {
                        override fun onAdClosed() {
                            startActivity(context, Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/${itemdata.web}")),null)

                        }
                    }

                           }

            }
        }
    }
    class viewholder(itemView:View): RecyclerView.ViewHolder(itemView){

        internal  var img:ImageView
       internal var name:TextView
        internal var card:CardView

        init {
            img=itemView.findViewById(R.id.imageView)
            name=itemView.findViewById(R.id.textView)
            card=itemView.findViewById(R.id.itemCard)
        }


    }




    }

   





