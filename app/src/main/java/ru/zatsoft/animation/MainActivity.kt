package ru.zatsoft.animation

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import ru.zatsoft.animation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var animation: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animation = AnimationUtils.loadAnimation(applicationContext,R.anim.fade)
        binding.ivRun.startAnimation(animation)
        animation = AnimationUtils.loadAnimation(applicationContext,R.anim.move)
        binding.btnStart.startAnimation(animation)
        binding.btnStart.setOnClickListener{
            startActivity(Intent(applicationContext, StartActivity::class.java))
        }

    }


}