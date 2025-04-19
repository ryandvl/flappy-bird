package game

import BOARD_SIZE
import PIPE_SIZE
import PIPE_Y
import dto.KeyDetector
import gfx.Window
import utils.Assets
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.KeyEvent
import java.util.*
import javax.swing.Timer

class Game {
    val assets: Assets = Assets()
    var size: Dimension = BOARD_SIZE
    val window: Window = Window(this, size)
    val bird = Bird(this)
    val pipes = ArrayList<Pipe>()
    val state = State(this)
    var score: Long = 0

    private val playKeyListener = PlayKeyListener()

    private val loop: Timer = Timer(1000 / 60, window.gamePanel) // 1000 / 60 = 16.6 = 60 FPS
    private val pipesLoop: Timer = Timer(1500) { // 1.5s
        placePipes()
    }

    inner class PlayKeyListener : KeyDetector() {
        override fun keyPressed(e: KeyEvent) {
            if (e.isAltDown || e.isControlDown || e.isShiftDown || e.keyCode == KeyEvent.VK_ESCAPE) return
            start()
        }
    }

    inner class EscKeyListener : KeyDetector() {
        override fun keyPressed(e: KeyEvent) {
            if (e.keyCode == KeyEvent.VK_ESCAPE)
                close()
        }
    }

    init {
        window.title = "Flappy Bird"
        Random()

        window.addKeyListener(playKeyListener)
        window.addKeyListener(EscKeyListener())
    }

    fun start() {
        if (state.type == GameState.OVER)
            restart()

        window.removeKeyListener(playKeyListener)

        state.type = GameState.STARTED
        window.addKeyListener(bird.movement)

        // Timers
        loop.start()
        pipesLoop.start()
    }

    private fun restart() {
        bird.reset()
        pipes.clear()
        score = 0
    }

    fun over() {
        stop()
        state.type = GameState.OVER

        Thread {
            Thread.sleep(200)
            window.addKeyListener(playKeyListener)
        }.start()
    }

    private fun stop() {
        window.removeKeyListener(playKeyListener)
        window.removeKeyListener(bird.movement)
        pipesLoop.stop()
        loop.stop()
    }

    fun close() {
        stop()
        window.dispose()
    }

    private fun placePipes() {
        val pipe = Pipe(this)

        val y = PIPE_Y
        val height = PIPE_SIZE.height

        val randomY: Int = (y - height / 4 - Math.random() * (height / 2)).toInt()
        val openingSpace = size.height / 4

        pipe.topPosition.y = randomY
        pipe.bottomPosition.y = pipe.topPosition.y + height + openingSpace

        pipes.add(pipe)
    }

    fun render(g: Graphics) {
        val graphics = g as Graphics2D

        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)

        window.render(graphics)
        state.render(graphics)
    }
}