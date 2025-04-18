package  dto

import java.awt.event.KeyEvent
import java.awt.event.KeyListener

open class KeyDetector : KeyListener {
    override fun keyPressed(e: KeyEvent) {}

    override fun keyTyped(e: KeyEvent?) {}
    override fun keyReleased(e: KeyEvent?) {}
}