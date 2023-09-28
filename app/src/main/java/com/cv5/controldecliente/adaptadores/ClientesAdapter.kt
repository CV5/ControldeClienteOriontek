package com.cv5.controldecliente.adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.recyclerview.widget.RecyclerView
import com.cv5.controldecliente.R
import com.cv5.controldecliente.databinding.ClienteItemListBinding
import com.cv5.controldecliente.models.Cliente

class ClientesAdapter(private val dataset: List<Cliente>) :
    RecyclerView.Adapter<ClientesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cliente_item_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemActual: Cliente = dataset.get(position)
        holder.enlazarItem(itemActual)

    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ClienteItemListBinding.bind(view)
        var contexto = view.context
        fun enlazarItem(itemActual: Cliente) {
                binding.tvNombre.text = "${itemActual.nombre} ${itemActual.apellido}"
                binding.tvEmail.text = itemActual.email
        }

    }
}