package com.example.pam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.editText).text = arguments?.getString("data") ?: ""

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            val text : String = view.findViewById<EditText>(R.id.editText).text.toString()
            val bundle = bundleOf("data" to text)
            view.findNavController().navigate(R.id.SecondFragment, bundle)
        }
    }
}