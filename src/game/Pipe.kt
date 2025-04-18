package game

import PIPE_SIZE
import dto.Position
import java.awt.Graphics
import java.awt.image.BufferedImage

class Pipe(game: Game) {
    val topPosition = Position(game.size.width, 0)
    val bottomPosition = Position(game.size.width, 0)
    var x: Int = game.size.width

    private val topImage: BufferedImage = game.assets.getImage("top_pipe")!!
    private val bottomImage: BufferedImage = game.assets.getImage("bottom_pipe")!!

    val size = PIPE_SIZE
    var passed: Boolean = false

    fun move(velocityX: Int) {
        topPosition.add(deltaX = velocityX)
        bottomPosition.add(deltaX = velocityX)
        x = topPosition.x
    }

    fun render(graphics: Graphics) {
        graphics.drawImage(topImage, topPosition.x, topPosition.y, size.width, size.height, null)
        graphics.drawImage(bottomImage, bottomPosition.x, bottomPosition.y, size.width, size.height, null)
    }
}