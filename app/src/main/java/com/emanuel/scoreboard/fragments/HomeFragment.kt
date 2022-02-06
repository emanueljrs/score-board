package com.emanuel.scoreboard.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emanuel.scoreboard.R
import com.emanuel.scoreboard.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private var namesPairA: String = ""
    private var namesPairB: String = ""
    private var pointsForSets: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setListeners()
    }

    override fun onResume() {
        super.onResume()
        clearTexts()
    }

    private fun setListeners() {
        binding.buttonStart.setOnClickListener {
            if (checkFieldPointsSets()) {
                checkFieldsNames()
                // Envia os argumentos para o fragment destino
                val action = HomeFragmentDirections.fromHomeFragmentToScoreFragment(
                    namesPairA = namesPairA,
                    namesPairB = namesPairB,
                    pointsForSets = pointsForSets
                )
                findNavController().navigate(action)
            }
        }
    }

    private fun clearTexts() {
        binding.run {
            editTextPairA.text?.clear()
            editTextPairA.requestFocus()
            editTextPairB.text?.clear()
            editTextPointsForSets.text?.clear()
        }
    }

    private fun checkFieldsNames() {
        namesPairA = if (binding.editTextPairA.text.toString().isNotEmpty()) {
            binding.editTextPairA.text.toString()
        } else {
            getString(R.string.pair_a)
        }

        namesPairB = if (binding.editTextPairB.text.toString().isNotEmpty()) {
            binding.editTextPairB.text.toString()
        } else {
            getString(R.string.pair_b)
        }
    }

    private fun checkFieldPointsSets() : Boolean {
        return if (binding.editTextPointsForSets.text.toString().isNotEmpty()
            && binding.editTextPointsForSets.text.toString().toInt() != 0) {
            pointsForSets = binding.editTextPointsForSets.text.toString().toInt()
            binding.layoutEditPointsForSets.error = null
            true
        } else {
            binding.layoutEditPointsForSets.error = getString(R.string.message_error_field_points_for_sets)
            false
        }
    }
}