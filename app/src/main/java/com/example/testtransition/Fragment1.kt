package com.example.testtransition


import android.content.Context
import android.os.Build
import android.os.Bundle
import android.transition.Fade
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    lateinit var imageView: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fragment1, container, false)
        imageView = rootView.findViewById<ImageView>(R.id.image)

        imageView.setOnClickListener {
            val fragment2 = Fragment2()
            val changeTransform = TransitionInflater.from(activity).inflateTransition(R.transition.change_image_transform)
            fragment2.sharedElementEnterTransition = changeTransform
            fragment2.sharedElementReturnTransition = changeTransform
            fragment2.exitTransition = Fade()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment2)
                    .addSharedElement(imageView, "android")
                    .addToBackStack(null).commit()
            } else {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment2)
                    .addToBackStack("2").commit()
            }
        }
        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                fragmentManager!!.beginTransaction()
                    .remove(fragmentManager!!.findFragmentById(R.id.container)!!).commit()
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }


}
