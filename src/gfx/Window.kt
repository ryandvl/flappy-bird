package gfx

import BACKGROUND_TRANSPARENT
import BORDER_COLOR
import game.Game
import gfx.components.RoundedPanel
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.Graphics2D
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.border.EmptyBorder

class Window(private val game: Game, size: Dimension) : JFrame() {
    val assets = game.assets
    var initialX: Int = 0
    var initialY: Int = 0

    init {
        iconImage = assets.resizeImageIcon(
            ImageIcon("${assets.assetsFolder}/images/bird.png"),
            128,
            128
        ).image

        isResizable = false
        defaultCloseOperation = EXIT_ON_CLOSE
        isUndecorated = true
        layout = BorderLayout()
        isFocusable = true
        this.size = size

        background = BACKGROUND_TRANSPARENT

        val contentPanel = RoundedPanel()
        val borderSize = 2
        contentPanel.border = EmptyBorder(borderSize, borderSize, borderSize, borderSize)
        contentPanel.layout = BorderLayout()
        contentPanel.setRoundAll(10)
        contentPanel.background = BORDER_COLOR
        contentPane = contentPanel

        add(TitleBar(game, this), BorderLayout.NORTH)
        add(GamePanel(game, this), BorderLayout.CENTER)
        pack()

        setLocationRelativeTo(null)

        requestFocusInWindow()
        isVisible = true
    }

    fun render(graphics: Graphics2D) {
        // Background
        graphics.drawImage(assets.getImage("background"), 0, 0, size.width, size.height, null)

        // Bird
        game.bird.draw(graphics)

        // Pipes
        for (pipe in game.pipes) {
            pipe.render(graphics)
        }

        // Score
    }
}