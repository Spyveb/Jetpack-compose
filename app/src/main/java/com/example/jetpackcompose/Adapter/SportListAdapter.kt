package com.example.jetpackcompose.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.jetpackcompose.R
import com.example.jetpackcompose.RetrofitResModel.payload
import kotlinx.android.synthetic.main.sport_list.view.*


class SportListAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<SportListAdapter.SportListHolder>() {
    var items: ArrayList<payload> = ArrayList<payload>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportListHolder {
        return SportListHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.sport_list, parent, false)
        )

    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SportListHolder, position: Int) {
        val item = items[position]
        if (item != null) {
            holder.bind(item, position)
        }
    }

    fun swapList(items: ArrayList<payload>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class SportListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: payload, position: Int) = with(itemView) {


            Glide.with(this)
                .load(item.image)
                .transition(DrawableTransitionOptions.withCrossFade(500))
                .into(imgAlbum)
            tvName.text = item.name

            mainCard.setOnClickListener {
                onClick(item.name)
            }
        }
    }
}