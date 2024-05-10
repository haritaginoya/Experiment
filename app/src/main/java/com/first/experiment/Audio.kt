package com.first.experiment

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity


class Audio : AppCompatActivity() {
    lateinit var videoView: VideoView
    lateinit var test: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio)

        test = findViewById(R.id.test)


        val mp: MediaPlayer = MediaPlayer.create(this, R.raw.abc)
        test.setOnClickListener {

            mp.stop()
        }

        videoView = findViewById(R.id.idVideoView)

        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.testvideo));

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)

        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}