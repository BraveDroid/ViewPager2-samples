package com.bravedroid.introslider.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bravedroid.introslider.R
import com.bravedroid.introslider.intro.model.IntroSlide

class IntroSliderAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slide_item_container, parent, false)
        return IntroSlideViewHolder(view)
    }

    override fun getItemCount(): Int = introSlides.size

    fun lastItem(): Int = introSlides.lastIndex

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) =
        holder.bind(introSlides[position])

    inner class IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textTitle = view.findViewById<TextView>(R.id.textTitle)
        private val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private val imageIcon = view.findViewById<ImageView>(R.id.imageSlideIcon)

        fun bind(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }
    }
}
