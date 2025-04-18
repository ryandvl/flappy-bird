package game

import BOARD_SIZE
import gfx.Window
import utils.Assets
import java.awt.Dimension
import java.awt.Graphics
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.util.*
import javax.swing.Timer

enum class GameState {
    WELCOME,
    STARTED,
    OVER
}

class Game : ActionListener {
    private val loop: Timer = Timer(1000 / 60, this) // 1000 / 60 = 16.6 = 60 FPS
    private val pipesLoop: Timer = Timer(1500) {
        placePipes()
    }

    var assets: Assets = Assets()
    var size: Dimension = BOARD_SIZE
    var window: Window = Window(this, size)
    val bird = Bird(this)
    val pipes = ArrayList<Pipe>()
    var state: GameState = GameState.WELCOME

    init {
        Random()
    }

    fun start() {
        window.title = "Flappy Bird"

        window.addKeyListener(bird.movement)

        // Timers
        loop.start()
        pipesLoop.start()
    }

    private fun update() {
        if (state == GameState.OVER) {
            pipesLoop.stop()
            loop.stop()
        }
    }

    fun stop() {
        pipesLoop.stop()
        loop.stop()
        window.dispose()
    }

    private fun placePipes() {
        val topPipe = Pipe(this, PipeType.TOP)

        val y = topPipe.position.y
        val height = topPipe.size.height

        val randomY: Int = (y - height / 4 - Math.random() * (height / 2)).toInt()
        val openingSpace = size.height / 4

        topPipe.position.y = randomY

        val bottomPipe = Pipe(this, PipeType.BOTTOM)
        bottomPipe.position.y = topPipe.position.y + height + openingSpace

        pipes.add(topPipe)
        pipes.add(bottomPipe)
    }

    fun render(graphics: Graphics) {
        window.render(graphics)
    }

    override fun actionPerformed(e: ActionEvent?) {
        bird.movement.move()
        window.repaint()

        update()
    }
}