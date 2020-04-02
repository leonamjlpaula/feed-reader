package br.com.leonam.feedreader

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class DownloadImageTask(private val imageView: ImageView): AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg p0: String?): Bitmap {
        val url = p0[0]
        val stream = URL(url).openStream()

        return BitmapFactory.decodeStream(stream)
    }

    override fun onPostExecute(result: Bitmap?) {
        imageView.setImageBitmap(result)
    }
}