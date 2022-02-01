package com.example.testtask.adapter

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.testtask.PlayerActivity
import com.example.testtask.R
import com.example.testtask.model.Response
import com.example.testtask.model.Video
import kotlin.text.StringBuilder

class VideoAdapter(private val context: Context, private val response: Response) :
    RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val image: ImageView = itemView.findViewById(R.id.iv_image)

        fun bind(listItem: Video) {
            title.text = listItem.title
            Glide.with(itemView.context)
                .load(getSplitUrl(listItem.sources[0], listItem.thumb))
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .apply(
                    RequestOptions
                        .centerCropTransform()
                        .circleCrop()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .priority(Priority.NORMAL).centerCrop()
                )
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("TAG", "onLoadFailed: ")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.d("TAG", "onResourceReady: ")
                        return false
                    }

                })
                .into(image)
        }

        private fun getSplitUrl(sources: String, thumb: String): String {
            val builder = StringBuilder()
            return builder.append(sources.substring(0, sources.lastIndexOf('/') + 1)).append(thumb)
                .toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(response.categories[0].videos[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PlayerActivity::class.java)
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return response.categories[0].videos.size
    }
}
