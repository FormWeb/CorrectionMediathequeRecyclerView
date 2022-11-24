package be.bxl.will.correctionrecyclerviewmediatheque.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import be.bxl.will.correctionrecyclerviewmediatheque.R
import be.bxl.will.correctionrecyclerviewmediatheque.models.Media
import org.w3c.dom.Text

class MediaAdapter(private val medias : List<Media>, private val listener: MediaItemListener) : RecyclerView.Adapter<MediaAdapter.ViewHolder>() {
    interface MediaItemListener {
        fun onClick(media : Media)
        fun onDelete(media : Media)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tvTitle : TextView = view.findViewById(R.id.tv_title_item_media)
        val tvType : TextView = view.findViewById(R.id.tv_type_item_media)
        val tvDuration = view.findViewById<TextView>(R.id.tv_duration_item_media)
        val btnDelete : ImageButton = view.findViewById(R.id.btn_delete_media_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_media, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMedia = medias[position]
        holder.tvTitle.text = currentMedia.title
        holder.tvType.text = currentMedia.type
        holder.tvDuration.text = currentMedia.duration.toString()

        holder.itemView.setOnClickListener {
            listener.onClick(currentMedia)
        }

        holder.btnDelete.setOnClickListener {
            listener.onDelete(currentMedia)
        }
    }

    override fun getItemCount(): Int {
        return medias.size
    }
}