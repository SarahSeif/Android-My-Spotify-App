package com.example.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MusicPlayerActivity extends AppCompatActivity {


    private ImageView pic;
    private TextView sTitle;
    private TextView sArtist;

    private SeekBar songSeekBar;
    private ImageButton btn_next,btn_previous;
    private ImageView btn_pause;

    static MediaPlayer myMediaPlayer;
    int position;

    ArrayList<Song> mysongs;
    Thread updateseekBar;
    ArrayList<artists> songArtists;
    int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        pic = (ImageView) findViewById(R.id.songImg);
       sTitle = (TextView) findViewById(R.id.musicTitle);
       sArtist = (TextView) findViewById(R.id.musicArtist);

        //get picture and name and artist of the song when clicked on a particular song
         Intent intent = getIntent();
         String picture = intent.getExtras().getString("Picture");
         String SongTitle = intent.getExtras().getString("SongTitle");
         Bundle bundle2 = intent.getExtras();
        songArtists = (ArrayList) bundle2.getParcelableArrayList("SongArtists");


        // String SongArtist = intent.getExtras().getParcelableArrayList("SongArtist");


        //set pic into imageview
        Picasso.get().load(picture).into(pic);
       // pic.setImageResource(picture);

        sTitle.setText(SongTitle);
        sTitle.setSelected(true);
       // sArtist.setText(SongArtist);
        String Artists = "";
        for (int i = 0 ; i< songArtists.size(); i++){
            if(songArtists.size() > 1 && i != songArtists.size() - 1) {
                Artists = Artists + songArtists.get(i).getName() + ", ";
            }
            else{
                Artists = Artists + songArtists.get(i).getName();
            }
            sArtist.setText(Artists);
            // name = name1 +"," +name2 ..
        }


        //activate the rest of the player
        songSeekBar = (SeekBar) findViewById(R.id.seekBar);
        btn_next = (ImageButton) findViewById(R.id.nextBtn);
        btn_previous = (ImageButton) findViewById(R.id.previousBtn);
        btn_pause = (ImageView) findViewById(R.id.pauseBtn);

        getSupportActionBar().setTitle("Now Playing");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        updateseekBar = new Thread(){
            @Override
            public void run() {
               int totalDuration = myMediaPlayer.getDuration();


               while (currentPosition < totalDuration){
                   try {
                       sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }

                   runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            currentPosition = myMediaPlayer.getCurrentPosition();
                            songSeekBar.setProgress(currentPosition);

                    }
                });

               }

            }
        };


        if(myMediaPlayer != null){
            myMediaPlayer.stop();
            myMediaPlayer.release();
       }



        Bundle bundle = intent.getExtras();

        mysongs = (ArrayList) bundle.getParcelableArrayList("songs");
        position = bundle.getInt("pos",0);

         Song song = mysongs.get(position);
         Uri u = Uri.parse(song.getPreview_url());
        myMediaPlayer = MediaPlayer.create(getApplicationContext(), u);
        myMediaPlayer.start();
        songSeekBar.setMax(myMediaPlayer.getDuration());
        updateseekBar.start();



        songSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                  myMediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             songSeekBar.setMax(myMediaPlayer.getDuration());

             if(myMediaPlayer.isPlaying()){
                 btn_pause.setImageResource(R.drawable.play);
                 myMediaPlayer.pause();
             }
             else{
                  btn_pause.setImageResource(R.drawable.pause);
                  myMediaPlayer.start();
             }

            }
        });

      btn_next.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              myMediaPlayer.stop();
              myMediaPlayer.release();

              position = ((position+1)%mysongs.size());
              Song song = mysongs.get(position);
              if(song.getPreview_url() == null){
                  position = ((position+1)%mysongs.size());
                   song = mysongs.get(position);
              }
              Uri u = Uri.parse(song.getPreview_url());
              myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
              Picasso.get().load(song.getAlbum().getImages().get(0).getUrl()).into(pic);
              //pic.setImageResource(song.getAlbum().getImages().);
              sTitle.setText(song.getName());
              sTitle.setSelected(true);
              //sArtist.setText(song.getArtist());
              String Artists = "";
              for (int i = 0 ; i< song.getArtists().size(); i++){
                  if(song.getArtists().size() > 1 && i != song.getArtists().size() - 1) {
                      Artists = Artists + song.getArtists().get(i).getName() + ", ";
                  }
                  else{
                      Artists = Artists + song.getArtists().get(i).getName();
                  }
                  sArtist.setText(Artists);
                  // name = name1 +"," +name2 ..
              }
              myMediaPlayer.start();

          }
      });

      btn_previous.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              myMediaPlayer.stop();
              myMediaPlayer.release();
              position = ((position - 1)<0)?(mysongs.size()-1):(position-1);
              Song song = mysongs.get(position);
              if(song.getPreview_url() == null){
                  position = ((position+1)%mysongs.size());
                  song = mysongs.get(position);
              }
              Uri u = Uri.parse(song.getPreview_url());
              myMediaPlayer = MediaPlayer.create(getApplicationContext(),u);
              Picasso.get().load(song.getAlbum().getImages().get(0).getUrl()).into(pic);
              //pic.setImageResource(song.getPicture());
              sTitle.setText(song.getName());
              sTitle.setSelected(true);
             // sArtist.setText(song.getArtist());
              String Artists = "";
              for (int i = 0 ; i< song.getArtists().size(); i++){
                  if(song.getArtists().size() > 1 && i != song.getArtists().size() - 1) {
                      Artists = Artists + song.getArtists().get(i).getName() + ", ";
                  }
                  else{
                      Artists = Artists + song.getArtists().get(i).getName();
                  }
                  sArtist.setText(Artists);
                  // name = name1 +"," +name2 ..
              }


              myMediaPlayer.start();
          }
      });




    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }


        return super.onOptionsItemSelected(item);
    }
}
