package br.com.leonam.feedreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: FeedAdapter
    val listItems = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_feeds)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = FeedAdapter(listItems, this)
        recyclerView.adapter = adapter

        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()
    }


    override fun onLoaded(newArticles: MutableList<Article>?) {
        listItems.clear()
        newArticles?.mapTo(listItems) {
            Item(it.title, it.author, it.date, it.source, it.enclosure.url)
        }
        adapter.notifyDataSetChanged()
    }

    override fun onLoadFailed() {}

    override fun onPreload() {}

    data class Item(
        val title: String,
        val author: String,
        val date: Long,
        val link: Uri,
        val image: String
    )
}
