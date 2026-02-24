package com.example.wearostictactoe

import androidx.compose.runtime.mutableStateListOf

class GameHistory {
    private val _history = mutableStateListOf<String>()
    val history: List<String> = _history

    fun addGame(winner: String) {
        _history.add(winner)
    }
}