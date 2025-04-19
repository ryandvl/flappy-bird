package game

import gfx.centerText
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D

enum class GameState {
    WELCOME,
    STARTED,
    OVER
}

class State(private val game: Game) {
    var type: GameState = GameState.WELCOME

    fun render(graphics: Graphics2D) {
        when (type) {
            GameState.OVER -> {
                graphics.color = Color(255, 100, 100)
                graphics.font = Font("bit5x3", Font.BOLD, 36)
                centerText(
                    graphics2D = graphics,
                    text = "Game Over",
                    size = game.size,
                    offsetY = -250,
                    outline = true,
                    outlineThickness = 6
                )

                graphics.color = Color.YELLOW
                graphics.font = Font("bit5x3", Font.BOLD, 24)
                centerText(
                    graphics2D = graphics,
                    text = "Score: ${game.score}",
                    size = game.size,
                    offsetY = -180,
                    outline = true,
                    outlineThickness = 4
                )

                graphics.color = Color.WHITE
                centerText(
                    graphics2D = graphics,
                    text = "Press any key to restart",
                    size = game.size,
                    offsetY = -120,
                    outline = true,
                    outlineThickness = 4
                )
            }

            GameState.WELCOME -> {
                graphics.font = Font("bit5x3", Font.BOLD, 28)
                graphics.color = Color.WHITE
                centerText(
                    graphics2D = graphics,
                    text = "Press any key to start",
                    size = game.size,
                    offsetY = -200,
                    outline = true,
                    outlineThickness = 4
                )
            }

            GameState.STARTED -> {
                graphics.font = Font("bit5x3", Font.BOLD, 32)
                graphics.color = Color.WHITE

                centerText(
                    graphics2D = graphics,
                    text = game.score.toString(),
                    size = game.size,
                    offsetY = -240,
                    outline = true,
                    outlineThickness = 4
                )
            }
        }
    }
}