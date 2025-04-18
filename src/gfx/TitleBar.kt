package gfx

import TITLE_BAR_COLOR
import TITLE_BAR_TEXT_COLOR
import game.Game
import gfx.components.Button
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.Frame
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionAdapter
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.border.CompoundBorder
import javax.swing.border.EmptyBorder
import javax.swing.border.MatteBorder

class TitleBar(private val game: Game, private val window: Window) : JPanel() {
    private inner class TitleLabel : JLabel() {
        init {
            text = "Flappy Bird"
            horizontalAlignment = SwingConstants.LEFT
            verticalAlignment = SwingConstants.CENTER
            foreground = TITLE_BAR_TEXT_COLOR
            font = Font("bit5x3", Font.BOLD, 18)
        }
    }

    private inner class MinimizeButton : Button(window, "images/icons/minimize.png") {
        init {
            this.addActionListener {
                window.state = Frame.ICONIFIED
            }
        }
    }

    private inner class CloseButton : Button(window, "images/icons/close.png") {
        override val backgroundHoverColor: Color = Color(200, 100, 100)

        init {
            this.addActionListener {
                game.close()
            }
        }
    }

    private inner class WindowButtons : JPanel() {
        init {
            isOpaque = false
            add(MinimizeButton())
            add(CloseButton())
        }
    }

    init {
        background = TITLE_BAR_COLOR
        val padding = 2
        val paddingBorder = EmptyBorder(padding, 6, padding, 6)
        val bottomLineBorder = MatteBorder(0, 0, 2, 0, Color(0, 0, 0, 30))

        border = CompoundBorder(paddingBorder, bottomLineBorder)
        layout = BorderLayout()

        this.addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent) {
                window.initialX = e.xOnScreen - window.location.x
                window.initialY = e.yOnScreen - window.location.y
            }
        })
        this.addMouseMotionListener(object : MouseMotionAdapter() {
            override fun mouseDragged(e: MouseEvent) {
                val newX = e.xOnScreen - window.initialX
                val newY = e.yOnScreen - window.initialY
                window.setLocation(newX, newY)
            }
        })

        add(TitleLabel(), BorderLayout.WEST)
        add(WindowButtons(), BorderLayout.EAST)
    }
}