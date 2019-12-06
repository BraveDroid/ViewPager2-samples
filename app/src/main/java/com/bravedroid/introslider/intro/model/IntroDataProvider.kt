package com.bravedroid.introslider.intro.model

import com.bravedroid.introslider.R

class IntroDataProvider {
    fun provideIntroSlides(): List<IntroSlide> = listOf(
        IntroSlide(
            "Sunlight",
            "Sunlight is the light and energy that comes from the sun",
            R.drawable.capture
        ),
        IntroSlide(
            "Pay Online",
            "Electronic bill payment is a feature of online, mobile and telephone banking",
            R.drawable.capture2
        ),
        IntroSlide(
            "Video_streaming",
            "Streaming media is multimedia that is constantly received by and presented to an end_use",
            R.drawable.capture3
        )
    )
}
