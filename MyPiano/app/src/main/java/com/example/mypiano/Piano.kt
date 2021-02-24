package com.example.mypiano

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypiano.databinding.FragmentPianoBinding
import kotlinx.android.synthetic.main.fragment_piano.view.*

class Piano : Fragment() {

    private var _binding:FragmentPianoBinding? = null
    private val binding get() = _binding!!

    private val naturals = listOf("C", "D", "E" ,"F", "G", "A", "B", "C2", "D2", "E2") // White keys on the piano

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPianoBinding.inflate(layoutInflater)
        val view = binding.root

        val fm = childFragmentManager
        val ft = fm.beginTransaction()

        naturals.forEach{
            val naturalKeys = NaturalKeysFragment.newInstance(it)

            naturalKeys.onKeyDown = {
                println("Piano key down $it")
            }

            naturalKeys.onKeyUp = {
                println("Piano key up $it")
            }

            ft.add(view.pianoKeys.id, naturalKeys, "note_$it")
        }

        ft.commit()

        return view
    }
}