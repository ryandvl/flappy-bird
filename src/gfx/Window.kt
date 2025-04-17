package gfx

import javax.swing.JFrame

class Window(title: String, width: Int, height: Int) : JFrame(title) {
    init {
        isResizable = false
        defaultCloseOperation = EXIT_ON_CLOSE
        setLocationRelativeTo(null)
        setSize(width, height)

        add(GamePanel(this))
        pack()

        isVisible = true
    }
}