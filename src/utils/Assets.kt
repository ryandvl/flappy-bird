package utils

import errors.FontException
import errors.ImageException
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import javax.swing.ImageIcon

open class Assets {
    private val images: MutableMap<String, BufferedImage> = mutableMapOf()

    val assetsFolder: String
        get() {
            return "assets"
        }

    companion object {
        fun createImageIcon(path: String): ImageIcon? {
            val imageUrl = object {}::class.java.classLoader.getResource(path)
            return imageUrl?.let { ImageIcon(it) }
        }
    }

    init {
        val imagePaths = listOf(
            "images/background.png",
            "images/bird.png",
            "images/bottom_pipe.png",
            "images/top_pipe.png"
        )

        for (imagePath in imagePaths) {
            loadImage(imagePath)
        }

        registerFont("fonts/bit5x3.ttf")
    }

    fun getImage(name: String): BufferedImage? {
        return images[name]
    }

    fun loadImage(imagePath: String): BufferedImage {
        val javaUrl = javaClass.classLoader.getResource("$assetsFolder/$imagePath")
        val image: BufferedImage

        try {
            image = ImageIO.read(javaUrl)
        } catch (_: Exception) {
            throw ImageException(imagePath)
        }

        val fileName: String = imagePath.substringAfterLast("/").substringBeforeLast(".")
        images[fileName] = image

        return image
    }

    fun resizeImageIcon(imageIcon: ImageIcon, width: Int, height: Int): ImageIcon {
        val originalImage = imageIcon.image
        val scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        return ImageIcon(scaledImage)
    }

    private fun registerFont(fontPath: String) {
        val javaUrl = javaClass.classLoader.getResourceAsStream("$assetsFolder/$fontPath")

        try {
            val font = Font.createFont(Font.TRUETYPE_FONT, javaUrl)
            val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()

            graphicsEnvironment.registerFont(font)
        } catch (e: Exception) {
            throw FontException("$assetsFolder/$fontPath")
        }
    }
}