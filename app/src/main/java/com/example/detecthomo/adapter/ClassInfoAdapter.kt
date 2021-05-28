package com.example.detecthomo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.detecthomo.databinding.CheckClassRvItemBinding
import com.example.detecthomo.reflectutil.ClassProperty

class ClassInfoAdapter: ListAdapter<Pair<ClassProperty?, String>, ClassInfoAdapter.ClassInfoViewHolder>(ClassInfoDiffCallback()) {

    class ClassInfoViewHolder(private val binding:CheckClassRvItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: Pair<ClassProperty?, String>){
            with(binding){
                classInfoItemTitle.text = item.first?.propertyName?:"Error"
                classInfoItemDetail.text = item.second
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CheckClassRvItemBinding.inflate(inflater,parent,false)
        return ClassInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassInfoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ClassInfoDiffCallback: DiffUtil.ItemCallback<Pair<ClassProperty?, String>>(){
        override fun areItemsTheSame(
            oldItem: Pair<ClassProperty?, String>,
            newItem: Pair<ClassProperty?, String>
        ): Boolean {
            return oldItem.first == newItem.first
        }

        override fun areContentsTheSame(
            oldItem: Pair<ClassProperty?, String>,
            newItem: Pair<ClassProperty?, String>
        ): Boolean {
            return newItem.second == oldItem.second
        }

    }

}