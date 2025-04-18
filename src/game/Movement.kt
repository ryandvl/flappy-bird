package game

import BOARD_SIZE
import dto.Vector2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Movement(private val game: Game, private val bird: Bird) : KeyListener {
    private val velocity = Vector2D(-4, 0)
    private val gravity: Int = 1

    fun move() {
        velocity.y += gravity
        bird.position.add(deltaY = velocity.y)

        for (pipe in game.pipes) {
            pipe.position.add(deltaX = velocity.x)

            if (checkCollision(bird, pipe))
                game.state = GameState.OVER
        }

        if (bird.position.y > BOARD_SIZE.height)
            game.state = GameState.OVER
    }

    fun checkCollision(bird: Bird, pipe: Pipe): Boolean {
        val aX = bird.position.x
        val aY = bird.position.y
        val aW = bird.size.width
        val aH = bird.size.height

        val bX = pipe.position.x
        val bY = pipe.position.y
        val bW = pipe.size.width
        val bH = pipe.size.height

        return aX < bX + bW &&
                aX + aW > bX &&
                aY < bY + bH &&
                aY + aH > bY
    }

    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_SPACE -> {
                velocity.y = -9
            }
        }
    }

    override fun keyTyped(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}
}