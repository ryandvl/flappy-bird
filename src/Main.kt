import game.Game
import javax.swing.SwingUtilities.invokeLater

fun main() {
    invokeLater {
        val game = Game()

        game.start()
    }
}