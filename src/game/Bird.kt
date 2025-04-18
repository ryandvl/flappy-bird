package game

import BOARD_SIZE
import dto.Position
import gfx.Window
import java.awt.Dimension
import java.awt.Graphics

class Bird(game: Game) {
    private var window: Window = game.window
    val movement = Movement(game, this)

    var position = Position(BOARD_SIZE.width / 8, BOARD_SIZE.height / 2, 0, 0)
    val size = Dimension(34, 24)
    private val image = window.assets.loadImage("images/bird.png")
    var canJump: Boolean = false

    fun draw(graphics: Graphics) {
        graphics.drawImage(image, position.x, position.y, size.width, size.height, null)
    }

    fun reset() {
        position.set(BOARD_SIZE.width / 8, BOARD_SIZE.height / 2)
        movement.velocity.y = 0
        canJump = true
    }
}