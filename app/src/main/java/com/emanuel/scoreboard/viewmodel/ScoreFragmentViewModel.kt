package com.emanuel.scoreboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreFragmentViewModel : ViewModel() {

    private val _namesPairA = MutableLiveData<String>()
    val namesPairA: LiveData<String> = _namesPairA

    private val _namesPairB = MutableLiveData<String>()
    val namesPairB: LiveData<String> = _namesPairB

    private val _setsA = MutableLiveData<Int>()
    val setsA: LiveData<Int> = _setsA

    private val _pointsA = MutableLiveData<Int>()
    val pointsA: LiveData<Int> = _pointsA

    private val _setsB = MutableLiveData<Int>()
    val setsB: LiveData<Int> = _setsB

    private val _pointsB = MutableLiveData<Int>()
    val pointsB: LiveData<Int> = _pointsB

    private val _pointsForSets = MutableLiveData<Int>()
    val pointsForSets: LiveData<Int> = _pointsForSets

    init {
        _pointsA.value = 0
        _pointsB.value = 0
        _setsA.value = 0
        _setsB.value = 0
    }

    fun setInitValues(
        pairA: String,
        pairB: String,
        pointsSets: Int
    ) {
        _namesPairA.value = pairA
        _namesPairB.value = pairB
        _pointsForSets.value = pointsSets
    }

    fun addPointsA() {
        _pointsA.value = _pointsA.value?.plus(1)
    }

    fun descPointsA() {
        if (_pointsA.value!! > 0) {
            _pointsA.value = _pointsA.value?.minus(1)
        }
    }

    fun addPointsB() {
        _pointsB.value = _pointsB.value?.plus(1)
    }

    fun descPointsB() {
        if (_pointsB.value!! > 0) {
            _pointsB.value = _pointsB.value?.minus(1)
        }
    }

    fun addSetsA() {
        if (_pointsA.value!! == _pointsForSets.value!!
            && _pointsB.value!! <= _pointsForSets.value?.minus(2)!!) {
            _setsA.value = _setsA.value?.plus(1)
            resetPoints()
        } else if (_pointsA.value!! >= _pointsForSets.value?.minus(1)!!
            && _pointsB.value!! >= _pointsForSets.value?.minus(1)!!) {
            if (_pointsA.value!! == _pointsB.value?.plus(2)) {
                _setsA.value = _setsA.value?.plus(1)
                resetPoints()
            }
        }
    }

    fun addSetsB() {
        if (_pointsB.value!! == _pointsForSets.value!!
            && _pointsA.value!! <= _pointsForSets.value?.minus(2)!!) {
            _setsB.value = _setsB.value?.plus(1)
            resetPoints()
        } else if (_pointsB.value!! >= _pointsForSets.value?.minus(1)!!
            && _pointsA.value!! >= _pointsForSets.value?.minus(1)!!) {
            if (_pointsB.value!! == _pointsA.value?.plus(2)) {
                _setsB.value = _setsB.value?.plus(1)
                resetPoints()
            }
        }
    }

    fun resetSets() {
        _setsA.value = 0
        _setsB.value = 0
    }

    fun resetPoints() {
        _pointsA.value = 0
        _pointsB.value = 0
    }
}