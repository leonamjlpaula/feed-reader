package br.com.leonam.feedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FeedAdapter(private val list: ArrayList<MainActivity.Item>, private val context: Context) :
    RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    class FeedViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val author: TextView = view.findViewById(R.id.author)
        val date: TextView = view.findViewById(R.id.date)
        val image: ImageView = view.findViewById(R.id.image)
        val btSeeMore: Button = view.findViewById(R.id.bt_see_more)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(
            R.layout.adapter, parent,
            false
        )

        return FeedViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.author.text = list[position].author
        holder.date.text = SimpleDateFormat(
            "dd/MM/yyyy",
            Locale("pt", "BR")
        ).format(list[position].date)

        holder.btSeeMore.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }

        DownloadImageTask(holder.image).execute(list[position].image)
    }

}