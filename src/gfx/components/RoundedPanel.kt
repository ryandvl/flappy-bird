package gfx.components

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.geom.Area
import java.awt.geom.Rectangle2D
import java.awt.geom.RoundRectangle2D
import javax.swing.JPanel
import kotlin.math.min

open class RoundedPanel : JPanel() {
    private var roundTopLeft: Int = 0
        set(value) {
            field = value
            repaint()
        }
    private var roundTopRight: Int = 0
        set(value) {
            field = value
            repaint()
        }
    private var roundBottomLeft: Int = 0
        set(value) {
            field = value
            repaint()
        }
    private var roundBottomRight: Int = 0
        set(value) {
            field = value
            repaint()
        }

    init {
        isOpaque = false
    }

    fun setRound(topLeft: Int?, topRight: Int? = null, bottomLeft: Int? = null, bottomRight: Int? = null) {
        topLeft?.let { roundTopLeft = it }
        topRight?.let { roundTopRight = it }
        bottomLeft?.let { roundBottomLeft = it }
        bottomRight?.let { roundBottomRight = it }
    }

    fun setRoundAll(radius: Int) {
        setRound(radius, radius, radius, radius)
    }

    override fun paintComponent(g: Graphics?) {
        val graphics = g?.create() as Graphics2D

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.color = background

        val area = createRoundTopLeft()
        if (roundTopRight > 0) {
            area.intersect(createRoundTopRight())
        }
        if (roundBottomLeft > 0) {
            area.intersect(createRoundBottomLeft())
        }
        if (roundBottomRight > 0) {
            area.intersect(createRoundBottomRight())
        }

        graphics.fill(area)
        graphics.dispose()

        super.paintComponent(g)
    }

    private fun createRoundTopLeft(): Area {
        val width: Double = width.toDouble()
        val height: Double = height.toDouble()

        val roundX = min(width, roundTopLeft.toDouble())
        val roundY = min(height, roundTopLeft.toDouble())

        val area = Area(RoundRectangle2D.Double(0.0, 0.0, width, height, roundX, roundY))
        area.add(Area(Rectangle2D.Double(roundX / 2, 0.0, width - roundX / 2, height)))
        area.add(Area(Rectangle2D.Double(0.0, roundY / 2, width, height - roundY / 2)))

        return area
    }

    private fun createRoundTopRight(): Area {
        val width: Double = width.toDouble()
        val height: Double = height.toDouble()

        val roundX = min(width, roundTopRight.toDouble())
        val roundY = min(height, roundTopRight.toDouble())

        val area = Area(RoundRectangle2D.Double(0.0, 0.0, width, height, roundX, roundY))
        area.add(Area(Rectangle2D.Double(0.0, 0.0, width - roundX / 2, height)))
        area.add(Area(Rectangle2D.Double(0.0, roundY / 2, width, height - roundY / 2)))

        return area
    }

    private fun createRoundBottomLeft(): Area {
        val width: Double = width.toDouble()
        val height: Double = height.toDouble()

        val roundX = min(width, roundBottomLeft.toDouble())
        val roundY = min(height, roundBottomLeft.toDouble())

        val area = Area(RoundRectangle2D.Double(0.0, 0.0, width, height, roundX, roundY))
        area.add(Area(Rectangle2D.Double(roundX / 2, 0.0, width - roundX / 2, height)))
        area.add(Area(Rectangle2D.Double(0.0, 0.0, width, height - roundY / 2)))

        return area
    }

    private fun createRoundBottomRight(): Area {
        val width: Double = width.toDouble()
        val height: Double = height.toDouble()

        val roundX = min(width, roundBottomRight.toDouble())
        val roundY = min(height, roundBottomRight.toDouble())

        val area = Area(RoundRectangle2D.Double(0.0, 0.0, width, height, roundX, roundY))
        area.add(Area(Rectangle2D.Double(0.0, 0.0, width - roundX / 2, height)))
        area.add(Area(Rectangle2D.Double(0.0, 0.0, width, height - roundY / 2)))

        return area
    }
}
