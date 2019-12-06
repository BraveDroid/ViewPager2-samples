package com.bravedroid.introslider.intro

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.bravedroid.introslider.R
import com.bravedroid.introslider.databinding.ActivityIntroBinding
import com.bravedroid.introslider.home.HomeActivity
import com.bravedroid.introslider.intro.model.IntroDataProvider
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro)
        val introSlides = IntroDataProvider()
            .provideIntroSlides()
        val introSliderAdapter =
            IntroSliderAdapter(introSlides)
        //todo  use data binding
        binding.viewPagerIntroSlider.adapter = introSliderAdapter

        setupIndicators(introSliderAdapter.itemCount)
        setCurrentIndicators(0)

        onPageChangeCallback = object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicators(position)
            }
        }
        binding.viewPagerIntroSlider.registerOnPageChangeCallback(onPageChangeCallback)

        binding.buttonIntroNext.setOnClickListener {
            if (binding.viewPagerIntroSlider.currentItem < introSliderAdapter.lastItem()) {
                binding.viewPagerIntroSlider.currentItem += 1
            } else {
                navigateToHomeActivity()
            }
        }

        binding.textViewIntroSkip.setOnClickListener {
            navigateToHomeActivity()
        }
    }

    private fun navigateToHomeActivity() {
        Intent(this, HomeActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.viewPagerIntroSlider.unregisterOnPageChangeCallback(onPageChangeCallback)
    }

    private fun setupIndicators(itemCount: Int) {
        val indicators = arrayOfNulls<ImageView>(itemCount)
        for (i in indicators.indices) {
            indicators[i] = ImageView(this).apply {
                //setImageDrawable(setupIndicatorDrawable())
                setImageResource(R.drawable.indicator_inactive)
                //setDrawable(R.drawable.indicator_inactive)
                layoutParams = setupImageLayoutParam()
            }
            binding.linearLayoutIntroIndicatorsContainer.addView(indicators[i])
        }
    }

//    private fun setupIndicatorDrawable(): Drawable? {
//        return ContextCompat.getDrawable(
//            this@IntroActivity,
//            R.drawable.indicator_inactive
//        )
//    }

    private fun setupImageLayoutParam(): LinearLayout.LayoutParams {
        val imageLayoutParam = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        val margin = resources.getDimension(R.dimen.indicatorLayoutMargin).toInt()
        imageLayoutParam.setMargins(margin, 0, margin, 0)
        return imageLayoutParam
    }

    private fun setCurrentIndicators(index: Int) {
        val childCount = binding.linearLayoutIntroIndicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = binding.linearLayoutIntroIndicatorsContainer[i] as ImageView
            val indicatorImageRes =
                if (i == index) R.drawable.indicator_active
                else R.drawable.indicator_inactive

            imageView.setImageResource(indicatorImageRes)
        }
    }
}

fun ImageView.setDrawable(@DrawableRes iconId: Int) {
    setImageDrawable(
        ContextCompat.getDrawable(
            context,
            iconId
        )
    )
}
