package gfx

import java.awt.Color
import java.awt.Dimension
import javax.swing.JPanel

class GamePanel(window: Window) : JPanel() {
    init {
        preferredSize = Dimension(window.width, window.height)
        background = Color.blue
    }
}