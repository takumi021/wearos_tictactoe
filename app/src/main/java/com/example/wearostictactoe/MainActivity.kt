package com.example.wearostictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.ButtonDefaults
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                LuxeTicTacToe()
            }
        }
    }
}

private enum class CellState { EMPTY, X, O }

@Composable
fun LuxeTicTacToe() {
    var board by remember { mutableStateOf(List(9) { CellState.EMPTY }) }
    var isXTurn by remember { mutableStateOf(true) }
    val gameHistory = remember { GameHistory() }

    val winner = remember(board) { calculateWinner(board) }
    val isDraw = winner == null && board.none { it == CellState.EMPTY }
    val status = when {
        winner != null -> {
            gameHistory.addGame("${winner.name} wins ✨")
            "${winner.name} wins ✨"
        }
        isDraw -> {
            gameHistory.addGame("Draw")
            "Draw • Tap New Game"
        }
        isXTurn -> "X turn"
        else -> "O turn"
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF120A2A), Color(0xFF07090F)),
                    radius = 500f
                )
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Luxe TicTacToe",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFDDC8FF)
            )

            Spacer(modifier = Modifier.height(4.dp))

            AnimatedContent(targetState = status, label = "status") { txt ->
                Text(
                    text = txt,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF99D9FF)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .size(152.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color(0x331C123A))
                    .border(
                        width = 2.dp,
                        brush = Brush.sweepGradient(
                            listOf(
                                Color(0xFF66F6FF),
                                Color(0xFFC88CFF),
                                Color(0xFFFF96C5),
                                Color(0xFF66F6FF)
                            )
                        ),
                        shape = RoundedCornerShape(26.dp)
                    )
                    .padding(6.dp)
            ) {
                Column(verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
                    repeat(3) { row ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            repeat(3) { col ->
                                val index = row * 3 + col
                                Cell(
                                    value = board[index],
                                    onTap = {
                                        if (winner == null && board[index] == CellState.EMPTY) {
                                            board = board.toMutableList().also {
                                                it[index] = if (isXTurn) CellState.X else CellState.O
                                            }
                                            isXTurn = !isXTurn
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (winner != null || isDraw) {
                Button(
                    onClick = {
                        board = List(9) { CellState.EMPTY }
                        isXTurn = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4A2A7A),
                        contentColor = Color(0xFFE8D8FF)
                    ),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text("New Game")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn {
                items(gameHistory.history) { game ->
                    Text(text = game, color = Color.White)
                }
            }
        }
    }
}

@Composable
private fun Cell(
    value: CellState,
    onTap: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(Color(0x55291945))
            .border(1.dp, Color(0x55C8A0FF), CircleShape)
            .clickable(onClick = onTap),
        contentAlignment = Alignment.Center
    ) {
        when (value) {
            CellState.X -> Text(
                text = "✕",
                color = Color(0xFFFF9BDF),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Black
            )

            CellState.O -> Canvas(modifier = Modifier.size(22.dp)) {
                drawArc(
                    color = Color(0xFF8BE7FF),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = Offset.Zero,
                    size = Size(size.width, size.height),
                    style = Stroke(width = 4f)
                )
            }

            CellState.EMPTY -> Text(
                text = "•",
                color = Color(0x55FFFFFF),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

private fun calculateWinner(board: List<CellState>): CellState? {
    val lines = listOf(
        listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8),
        listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8),
        listOf(0, 4, 8), listOf(2, 4, 6)
    )

    for ((a, b, c) in lines) {
        if (board[a] != CellState.EMPTY && board[a] == board[b] && board[a] == board[c]) {
            return board[a]
        }
    }
    return null
}
