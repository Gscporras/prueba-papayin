package com.whitestudios.papayin.activities.trailer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.whitestudios.papayin.R
import com.whitestudios.papayin.databinding.ActivityTrailerBinding

class TrailerActivity : YouTubeBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_trailer) as ActivityTrailerBinding
        playVideo(intent.getStringExtra(KEY_EXTRA), binding.playerView)
    }

    private fun playVideo(videoId: String, youTubePlayerView: YouTubePlayerView) {
        youTubePlayerView.initialize(getString(R.string.API_KEY),
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    b: Boolean
                ) {
                    youTubePlayer.cueVideo(videoId)
                    youTubePlayer.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) = Unit
            })
    }

    companion object {

        private const val KEY_EXTRA = "key_video_extra"

        fun newIntent(activity: Activity, key: String): Intent {
            return Intent(
                activity,
                TrailerActivity::class.java
            ).putExtra(KEY_EXTRA, key)
        }
    }
}
