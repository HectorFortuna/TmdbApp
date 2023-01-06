package com.hectorfortuna.tmdbapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hectorfortuna.tmdbapp.databinding.ActivitySplashScreenBinding
import com.hectorfortuna.tmdbapp.ui.home.MainActivity

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation()

    }

    private fun animation() {
        repeat(2000) {
            binding.icTmdbLogo.alpha = 0f
            binding.icTmdbLogo.apply {
                animate()
                    .scaleX(2.3f)
                    .scaleY(2.3f)
                    .alpha(1f)
                    .setDuration(1200)
                    .withEndAction {
                        val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
            }
        }
    }
}