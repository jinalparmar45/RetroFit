package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class MoneyAdapter(private var moneyList: List<Money>):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.money_list_layout, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemVH = moneyList[position]
        holder.amount.text = itemVH.amount.toString()
        holder.type.text = itemVH.type
        holder.sharedin.text = itemVH.shared_in.toString()
    }

    override fun getItemCount(): Int {
      return moneyList.size
    }

    fun setItem(moneylist: ArrayList<Money>) {
       this.moneyList =   moneylist
        notifyDataSetChanged()
    }
}
class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var amount: TextView = view.findViewById(R.id.amount)
    var type: TextView = view.findViewById(R.id.type)
    var sharedin: TextView = view.findViewById(R.id.sharedin)



    }