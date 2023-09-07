package com.d204.algo

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.vectordrawable.graphics.drawable.Animatable2Compat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.d204.algo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bubbleTransition: GifDrawable

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setAnimation()
        setContentView(binding.root)
        setupNavHost()
//        var keyHash = Utility.getKeyHash(this)
//        Log.d(TAG, "onCreate: $keyHash")
//        showSnackBar(binding.root, "ㅎㅇ")
    }

    private fun setupNavHost() {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
    }

    private fun removeAnimation() {
        val animation = AlphaAnimation(1.0f, 0.0f) // 투명도 1.0에서 0.0으로 애니메이션
        animation.duration = 1500 // 1.5초 동안
        binding.activityMainUpperBg.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.activityMainUpperBg.visibility = View.GONE // 뷰를 숨기거나
            }

            override fun onAnimationRepeat(animation: Animation?) {}
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setAnimation() {
        glide.asGif()
            .load(R.drawable.bubble_transition)
            .listener(object : RequestListener<com.bumptech.glide.load.resource.gif.GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<com.bumptech.glide.load.resource.gif.GifDrawable>?,
                    isFirstResource: Boolean,
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: com.bumptech.glide.load.resource.gif.GifDrawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<com.bumptech.glide.load.resource.gif.GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean,
                ): Boolean {
                    removeAnimation()

                    bubbleTransition = resource!!
                    bubbleTransition.setLoopCount(1)

                    bubbleTransition.registerAnimationCallback(object :
                        Animatable2Compat.AnimationCallback() {
                        override fun onAnimationEnd(drawable: Drawable?) {
                            binding.transitionAnim.visibility = View.GONE
                        }
                    })
                    return false
                }
            })
            .into(binding.transitionAnim)
    }
}
