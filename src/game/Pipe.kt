package game

import dto.Position
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage

enum class PipeType {
    TOP,
    BOTTOM
}

class Pipe(game: Game, private val type: PipeType) {
    val position = Position(game.size.width, 0)
    val size = Dimension(64, 512)
    var passed: Boolean = false

    private val image: BufferedImage = game.assets.getImage(
        when (type) {
            PipeType.TOP -> "top_pipe"
            PipeType.BOTTOM -> "bottom_pipe"
        }
    )!!

    fun render(graphics: Graphics) {
        graphics.drawImage(image, position.x, position.y, size.width, size.height, null)
    }
}