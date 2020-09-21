package com.arpit.simplerecylerviewexample.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arpit.simplerecylerviewexample.R
import com.arpit.simplerecylerviewexample.models.heros
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_home.view.*

class recyclerAdaptor(val context: Context ,val itemView:ArrayList<heros>):RecyclerView.Adapter<recyclerAdaptor.recyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerViewHolder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.single_home,parent,false)
        return recyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
      return itemView.size
    }

    override fun onBindViewHolder(holder: recyclerViewHolder, position: Int) {
      val item= itemView[position]
        holder.name.text=item.name
        Picasso.get().load(item.image_url).into(holder.image)
    }

    class recyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.nameOfTheHeros)
        val image: ImageView = view.findViewById(R.id.imageheros)
    }


}