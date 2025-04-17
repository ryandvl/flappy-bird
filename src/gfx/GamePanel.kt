package gfx

import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import javax.swing.JPanel

class GamePanel(window: Window) : JPanel() {
    private val assets = Assets()

    init {
        preferredSize = Dimension(window.width, window.height)
        background = Color.black

        assets.loadImage("background.png")
        assets.loadImage("bird.png")
        assets.loadImage("bottom_pipe.png")
        assets.loadImage("top_pipe.png")
    }

    private fun draw(graphics: Graphics) {
        graphics.drawImage(assets.getImage("background"), 0, 0, preferredSize.width, preferredSize.height, null)
    }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        draw(g)
    }
}