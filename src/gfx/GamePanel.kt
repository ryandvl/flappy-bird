package gfx

import game.Game
import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JPanel
import javax.swing.SwingUtilities

class GamePanel(private val game: Game, window: Window) : JPanel(), ActionListener {
    init {
        preferredSize = Dimension(window.width, window.height)
        background = Color.black

        SwingUtilities.invokeLater {
            game.canRender = true
        }
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)

        if(game.canRender) game.render(g)
    }

    override fun actionPerformed(e: ActionEvent?) {
        game.bird.movement.move()
        repaint()
    }
}