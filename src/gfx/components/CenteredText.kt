package gfx

import java.awt.Color
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics2D

fun centerText(
    graphics2D: Graphics2D,
    text: String,
    font: Font,
    size: Dimension,
    offsetX: Int = 0,
    offsetY: Int = 0,
    outline: Boolean = false,
    outlineColor: Color = Color.BLACK,
    outlineThickness: Int = 1
) {
    val fontMetrics = graphics2D.fontMetrics

    val textWidth = fontMetrics.stringWidth(text)
    val textHeight = fontMetrics.ascent

    val x = offsetX + (size.width - textWidth) / 2
    val y = offsetY + (size.height / 2) + (textHeight / 2)

    val lastColor = graphics2D.color

    if (outline) {
        graphics2D.color = outlineColor
        for (i in -outlineThickness..outlineThickness) {
            for (j in -outlineThickness..outlineThickness) {
                if (i != 0 || j != 0) {
                    graphics2D.drawString(text, x + i, y + j)
                }
            }
        }
    }

    graphics2D.color = lastColor
    graphics2D.drawString(text, x, y)
}