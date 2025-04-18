package gfx.components

import BACKGROUND_TRANSPARENT
import BUTTON_HOVER_COLOR
import gfx.Window
import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.ImageIcon
import javax.swing.JButton

abstract class Button(window: Window, imagePath: String, width: Int = 20, height: Int = 20) : JButton(
    window.assets.resizeImageIcon(
        ImageIcon("${window.assets.assetsFolder}/$imagePath"),
        width,
        height
    )
) {
    open val backgroundNormalColor: Color = BACKGROUND_TRANSPARENT
    open val backgroundHoverColor: Color = BUTTON_HOVER_COLOR

    init {
        layout = FlowLayout(FlowLayout.CENTER, 0, 0)

        foreground = Color.WHITE
        background = BACKGROUND_TRANSPARENT
        isBorderPainted = false
        isFocusPainted = false
        isContentAreaFilled = false

        cursor = Cursor(Cursor.HAND_CURSOR)
        preferredSize = Dimension(width, height)
        minimumSize = Dimension(width, height)
        maximumSize = Dimension(width, height)
        this.setSize(width, height)

        this.addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent) {
                onEnterHover(e)
            }

            override fun mouseExited(e: MouseEvent) {
                onLeaveHover(e)
            }
        })

        this.addMouseListener(object : MouseAdapter() {
            override fun mouseEntered(e: MouseEvent) {
                onEnterHover(e)
            }

            override fun mouseExited(e: MouseEvent) {
                onLeaveHover(e)
            }
        })
    }

    open fun onEnterHover(e: MouseEvent) {
        background = backgroundHoverColor
        isOpaque = true
    }

    open fun onLeaveHover(e: MouseEvent) {
        background = backgroundNormalColor
        isOpaque = false
    }
}
