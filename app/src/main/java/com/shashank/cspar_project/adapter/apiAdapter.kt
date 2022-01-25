package com.shashank.cspar_project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shashank.cspar_project.R
import com.shashank.cspar_project.model.ApiResponse
import kotlinx.android.synthetic.main.single_user.view.*


class apiAdapter: RecyclerView.Adapter<apiAdapter.MyViewHolder>(){

    private var myList = emptyList<ApiResponse>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_user, parent, false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.name_edt.text = myList[position].success[position].name
        holder.itemView.email_edt.text = myList[position].success[position].email




    }

    fun setData(newList: ApiResponse){
        myList = listOf(newList)
        notifyDataSetChanged()
    }
}