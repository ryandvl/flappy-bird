import gfx.Window
import javax.swing.SwingUtilities.invokeLater

fun main() {
    invokeLater {
        val window = Window("Flappy Bird", 360, 640)

        println("Stopping process...")
    }
}