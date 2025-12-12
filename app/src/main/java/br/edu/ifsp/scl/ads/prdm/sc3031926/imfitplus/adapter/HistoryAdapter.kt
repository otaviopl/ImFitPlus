package br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.R
import br.edu.ifsp.scl.ads.prdm.sc3031926.imfitplus.model.User
class HistoryAdapter(private val userList: List<User>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    // 1. Cria o layout do item (O "Carimbo")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)
        return HistoryViewHolder(view)
    }

    // 2. Preenche os dados no layout
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val user = userList[position]

        // TÍTULO: O nome do usuário
        holder.tvName.text = user.name

        // SUBTÍTULO: Vamos formatar uma string bonita com as infos principais.
        // Exemplo visual: "IMC: 24.5 (Peso Ideal) • 12/12/2023"

        val dataFormatada = user.createdAt ?: "Data desc." // Se a data for null, mostra msg padrão

        val detalhes = "IMC: ${user.imc} (${user.imcCategory})\nData: $dataFormatada"

        holder.tvDetails.text = detalhes

        // Dica extra: Se você quiser mudar a cor do ícone ou texto baseado no IMC
        // (ex: Vermelho se for obesidade), você pode fazer um "if" aqui.
        /*
        if (user.imcCategory.contains("Obesidade")) {
            holder.tvName.setTextColor(Color.RED)
        }
        */
    }

    // 3. Quantidade de itens
    override fun getItemCount(): Int = userList.size

    // 4. O ViewHolder (Gaveta de referências)
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvHistoryName)
        val tvDetails: TextView = itemView.findViewById(R.id.tvHistoryDetails)
        // val imgAvatar: ImageView = itemView.findViewById(R.id.imgAvatar)
    }
}