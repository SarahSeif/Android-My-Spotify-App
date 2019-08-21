package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomMusicAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Song> music;
    private MediaPlayer mediaPlayer;
    private boolean flag = true;
    ArrayList<Song> models = new ArrayList<>();

    public CustomMusicAdapter(Context context, int layout, ArrayList<Song> music) {
        this.context = context;
        this.layout = layout;
        this.music = music;
    }

    @Override
    public int getCount() {
        return music.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    private class ViewHolder{

        TextView songTitleTv,artistNameTv;
        ImageView ivPlay,ivStop;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       final ViewHolder viewHolder;

       if(convertView == null){
           viewHolder = new ViewHolder();
           LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

           convertView = layoutInflater.inflate(layout,null);
           viewHolder.songTitleTv = (TextView) convertView.findViewById(R.id.songTitleTv);
           viewHolder.artistNameTv = (TextView) convertView.findViewById(R.id.artistNameTv);
           viewHolder.ivPlay = (ImageView) convertView.findViewById(R.id.play);
           viewHolder.ivStop = (ImageView) convertView.findViewById(R.id.stop);


           convertView.setTag(viewHolder);
       }
       else{
           viewHolder = (ViewHolder) convertView.getTag();

       }

       final Song song = music.get(position);
       viewHolder.songTitleTv.setText(song.getName());

        String AllArtists = "";
       for (int i = 0 ; i< song.getArtists().size(); i++){
           if(song.getArtists().size() > 1 && i != song.getArtists().size() - 1) {
               AllArtists = AllArtists + song.getArtists().get(i).getName() + ", ";
           }
           else{
               AllArtists = AllArtists + song.getArtists().get(i).getName();
           }
           viewHolder.artistNameTv.setText(AllArtists);
      // name = name1 +"," +name2 ..
       }



       //play music using play button
        viewHolder.ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag){
                    Uri myUri = Uri.parse(song.getPreview_url());
                    mediaPlayer = MediaPlayer.create(context,myUri);
                    flag = false;
                }
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    viewHolder.ivPlay.setImageResource(R.drawable.play);
                }
                else{
                    mediaPlayer.start();
                    viewHolder.ivPlay.setImageResource(R.drawable.pause);
                }

            }
        });


        //stop music using stop button
        viewHolder.ivStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!flag){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                   flag = true;
                }
                viewHolder.ivPlay.setImageResource(R.drawable.play);

            }
        });
        //text toast
        viewHolder.songTitleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText((context),music.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.artistNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ss = "";
                for (int i = 0 ; i < song.getArtists().size(); i++){
                    if(song.getArtists().size() > 1 && i != song.getArtists().size() - 1) {
                        ss = ss+ music.get(position).getArtists().get(i).getName()+ ", ";
                    }
                   else{
                        ss = ss+ music.get(position).getArtists().get(i).getName();
                    }
                }
                Toast.makeText((context),ss,Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    public void setData(ArrayList<Song> data) {
        if (data != null) {
            //models.clear();
            this.music.addAll(data);
            this.notifyDataSetChanged();
        }else {
            Log.i("no data", "'no data provided");
        }
    }
}
