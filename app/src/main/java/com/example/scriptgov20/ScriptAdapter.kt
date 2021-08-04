package com.example.scriptgov20

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scriptgov20.databinding.ItemScriptBinding

class ScriptAdapter (
    var scripts: List<Script>
        ) : RecyclerView.Adapter<ScriptAdapter.ScriptViewHolder>() {

    private lateinit var _binding: ItemScriptBinding
    inner class  ScriptViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScriptViewHolder { 
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_script, parent, false)
        _binding = ItemScriptBinding.bind(view)
        return ScriptViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScriptViewHolder, position: Int) {

        holder.itemView .apply {
            _binding.tvTitle.text = scripts[position].title
        }
    }

    override fun getItemCount(): Int {
        return scripts.size
    }
}