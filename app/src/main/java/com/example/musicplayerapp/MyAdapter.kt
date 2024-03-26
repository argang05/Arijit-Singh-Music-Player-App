package com.example.musicplayerapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context : Activity , val dataList : List<Data>)
    : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Create a view in case the layout manager fails to create the view for the data:
        //called when recycler view needs a new ViewHolder of given type to represent the item:
        val itemView = LayoutInflater.from(context).inflate(
            R.layout.each_item, parent, false
        );

        return MyViewHolder(itemView);
    }

    override fun getItemCount(): Int {
        //Returns total number of items in dataset held by the adapter.
        return dataList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Called by RecyclerView to display data at specified position.
        //Populate the data into the view.
        val currentData = dataList[position];

        //Getting the music track preview mp3 file:
        val mediaPlayer = MediaPlayer.create(context , currentData.preview.toUri())

        //Get the track name:
        holder.trackName.text = currentData.title;

        //Get the track image:
        Picasso.get().load(currentData.album.cover).into(holder.trackImage);

        //Setting the play and pause functionality:
        holder.playButton.setOnClickListener {
            //Start the music:
            mediaPlayer.start();
        }

        holder.pauseButton.setOnClickListener {
            //Stop/Pause the music:
            mediaPlayer.stop();
        }
    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        //Used to hold the data:
        //Create all the views that we want to display:

        val trackImage : ImageView;
        val trackName : TextView;
        val playButton : ImageButton;
        val pauseButton : ImageButton;

        init{
            trackImage = itemView.findViewById(R.id.musicTrackImage);
            trackName = itemView.findViewById(R.id.musicTrackName);
            playButton = itemView.findViewById(R.id.playBtn);
            pauseButton = itemView.findViewById(R.id.pauseBtn);
        }

    }
}