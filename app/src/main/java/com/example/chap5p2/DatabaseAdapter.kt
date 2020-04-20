package com.example.chap5p2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*

class DatabaseAdapter(private val listNote : List<Item>) : RecyclerView.Adapter<DatabaseAdapter.ViewHolder>() {
    private lateinit var listener: OnClickListenerCallback<Item>
    fun setOnClickListener(listenerCallback: OnClickListenerCallback<Item>) {
        this.listener = listenerCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    private val data: MutableList<Item> = mutableListOf()

    override fun getItemCount(): Int = listNote.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listNote[position], position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, position: Int) {
            itemView.tv_tanggal.text = item.title
            itemView.tv_memo.text = item.content
            listener.let {
                itemView.setOnClickListener {
                    listener.onClickListener(item, position)
                }
            }

        }
    }

    interface OnClickListenerCallback<T> {
        fun onClickListener(data: T, position: Int)
    }

}