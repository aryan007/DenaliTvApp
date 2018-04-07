package info.androidhive.cardview;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by thukralp on 4/2/18.
 */

public class DetailPageActivity extends AppCompatActivity {
    private ExpandableHeightGridView gridview;
    private GridviewDetailsAdapter gridviewAdapter;
    private ArrayList<Bean> beanclassArrayList;
    private int[] IMAGEgrid = {R.drawable.album1, R.drawable.album2, R.drawable.album3, R.drawable.album4};
    private String[] TITLeGgrid = {"3 Idiots", "Wedding Crashers", "The Shawshank Redemption", "A Lot like Love"};
    private String[] DIscriptiongrid = {"A Jorney of 3 friends", "Comedy about Crashing Weddings", "A journey about men in jail shawshank", "A Love story"};
    private String[] Dategrid = {"Explore Now!", "Explore Now!", "Discover now!", "Discover now!"};
    private AppCompatButton requestVideo;
    private MyTextView videoTimerText;
    private RelativeLayout requestVideoLayout;
    private ImageView play_button;
    private ImageView videoThumbnailImageView;
    private Movie selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_detail);
        Bundle bundle = getIntent().getExtras();
        selectedMovie = (Movie) bundle.getSerializable("movie");
        gridview = (ExpandableHeightGridView) findViewById(R.id.gridview);
        requestVideo = (AppCompatButton) findViewById(R.id.requestVideo);
        videoTimerText = (MyTextView) findViewById(R.id.videoTimerText);
        requestVideoLayout = (RelativeLayout) findViewById(R.id.requestVideoLayout);
        play_button = (ImageView) findViewById(R.id.play_button);
        videoThumbnailImageView = (ImageView) findViewById(R.id.videoThumbnailImageView);

        videoThumbnailImageView.setImageDrawable(getResources().getDrawable(selectedMovie.getDetailThumbnail()));

        if (selectedMovie.isCached()) {
            play_button.setVisibility(View.VISIBLE);
            videoTimerText.setVisibility(View.VISIBLE);
            requestVideo.setVisibility(View.GONE);
            videoTimerText.setText("Watch Now!");
        } else {
            play_button.setVisibility(View.GONE);
            videoTimerText.setVisibility(View.GONE);
            requestVideo.setVisibility(View.VISIBLE);
        }
        requestVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRequestVideoTimer();
            }
        });

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideo();
            }
        });

        beanclassArrayList = new ArrayList<Bean>();

        for (int i = 0; i < IMAGEgrid.length; i++) {

            Bean beanclass = new Bean(IMAGEgrid[i], TITLeGgrid[i], DIscriptiongrid[i], Dategrid[i]);
            beanclassArrayList.add(beanclass);

        }

        gridviewAdapter = new GridviewDetailsAdapter(DetailPageActivity.this, beanclassArrayList);
        gridview.setExpanded(true);

        gridview.setAdapter(gridviewAdapter);
    }

    private void startRequestVideoTimer() {
        videoTimerText.setVisibility(View.VISIBLE);
        requestVideo.setVisibility(View.GONE);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                videoTimerText.setText("Seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                videoTimerText.setText("Your movie is available now!");
                play_button.setVisibility(View.VISIBLE);
            }

        }.start();
    }

    private void playVideo() {
        Intent playVideoIntent = new Intent(this, VideoPlayerActivity.class);
        playVideoIntent.putExtra("movieUrl", selectedMovie.getUrl());
        startActivity(playVideoIntent);
    }
}
