package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.model.User

class HistoryAdapter(private val userList: List<User>) : RecyclerView.Adapter<HistoryAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.tvName.text = user.name
        holder.tvDate.text = user.createdAt
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }
}
