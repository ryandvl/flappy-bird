package game

import BOARD_SIZE
import dto.Vector2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

class Movement(private val game: Game, private val bird: Bird) : KeyListener {
    val velocity = Vector2D(-4, 0)
    private val gravity: Int = 1

    fun move() {
        velocity.y += gravity
        bird.position.add(deltaY = velocity.y)

        for (pipe in game.pipes) {
            pipe.move(velocity.x)

            if (!pipe.passed && bird.position.x > pipe.x + pipe.size.width) {
                pipe.passed = true
                if (game.score >= Long.MAX_VALUE) {
                    return game.over()
                }
                game.score++
            }

            if (checkCollision(bird, pipe))
                game.over()
        }

        if (bird.position.y > BOARD_SIZE.height)
            game.over()
    }

    private fun checkCollision(bird: Bird, pipe: Pipe): Boolean {
        val aX = bird.position.x
        val aY = bird.position.y
        val aW = bird.size.width
        val aH = bird.size.height

        val bW = pipe.size.width
        val bH = pipe.size.height
        val bX = pipe.x

        val bTY = pipe.topPosition.y
        val bBY = pipe.bottomPosition.y

        return aX < bX + bW &&
                aX + aW > bX &&
                (aY < bTY + bH &&
                        aY + aH > bTY ||
                        aY < bBY + bH &&
                        aY + aH > bBY)
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