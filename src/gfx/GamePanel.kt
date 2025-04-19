package gfx

import game.Game
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel

class GamePanel(private val game: Game, window: Window) : JPanel(), ActionListener {
    init {
        preferredSize = Dimension(window.width, window.height)
        background = Color.black
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        game.render(g)
    }

    override fun actionPerformed(e: ActionEvent?) {
        game.bird.movement.move()
        repaint()
    }
}