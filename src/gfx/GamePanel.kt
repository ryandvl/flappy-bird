package gfx

import game.Game
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

class GamePanel(private val game: Game, private val window: Window) : JPanel() {
    init {
        preferredSize = Dimension(window.width, window.height)
        background = Color.black
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        game.render(g)
    }
}