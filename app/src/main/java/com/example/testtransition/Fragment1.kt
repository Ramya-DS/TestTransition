package com.example.testtransition


import android.os.Bundle
import android.os.Handler
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 */
class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_fragment1, container, false)
        val textView = rootView.findViewById<TextView>(R.id.text)
        val imageView = rootView.findViewById<ImageView>(R.id.image)

        val handler = Handler()
        handler.postDelayed({
            textView.visibility = INVISIBLE
            val fragment2 = Fragment2()
            fragment2.enterTransition = Fade().let {
                it.duration = 100
                it
            }
            activity!!.supportFragmentManager.beginTransaction().replace(R.id.container, fragment2)
                .addToBackStack(null).addSharedElement(imageView, "android").commit()
        }, 500)


        return rootView
    }


}
