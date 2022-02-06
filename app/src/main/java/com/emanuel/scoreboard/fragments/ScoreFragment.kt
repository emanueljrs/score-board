package com.emanuel.scoreboard.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.emanuel.scoreboard.R
import com.emanuel.scoreboard.databinding.FragmentScoreBinding
import com.emanuel.scoreboard.viewmodel.ScoreFragmentViewModel

class ScoreFragment : Fragment(R.layout.fragment_score) {

    private lateinit var binding: FragmentScoreBinding

    private val args: ScoreFragmentArgs by navArgs()

    private val viewModel: ScoreFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentScoreBinding.bind(view)

        setListeners()
        setObservers()

        viewModel.setInitValues(
            pairA = args.namesPairA,
            pairB = args.namesPairB,
            pointsSets = args.pointsForSets
        )
    }

    private fun setListeners() {
        binding.run {
            buttonAddA.setOnClickListener {
                viewModel.addPointsA()
                viewModel.addSetsA()
            }

            buttonLessA.setOnClickListener {
                viewModel.descPointsA()
            }

            buttonAddB.setOnClickListener {
                viewModel.addPointsB()
                viewModel.addSetsB()
            }

            buttonLessB.setOnClickListener {
                viewModel.descPointsB()
            }

            buttonResetPoints.setOnClickListener {
                viewModel.resetPoints()
            }

            buttonResetSets.setOnClickListener {
                viewModel.resetSets()
            }
        }
    }

    private fun setObservers() {
        viewModel.pointsA.observe(viewLifecycleOwner) { valueA ->
            binding.textScoreA.text = valueA.toString()
        }

        viewModel.pointsB.observe(viewLifecycleOwner) { valueB ->
            binding.textScoreB.text = valueB.toString()
        }

        viewModel.namesPairA.observe(viewLifecycleOwner) { namesA ->
            binding.textNamesPairA.text = namesA
        }

        viewModel.namesPairB.observe(viewLifecycleOwner) { namesB ->
            binding.textNamesPairB.text = namesB
        }

        viewModel.setsA.observe(viewLifecycleOwner) { setA ->
            binding.textSetsA.text = setA.toString()
        }

        viewModel.setsB.observe(viewLifecycleOwner) { setB ->
            binding.textSetsB.text = setB.toString()
        }

        viewModel.pointsForSets.observe(viewLifecycleOwner) { pointsForSets ->
            binding.textValuePointsForSets.text = pointsForSets.toString()
        }
    }
}