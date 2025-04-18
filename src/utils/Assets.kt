package utils

import errors.FontException
import errors.ImageException
import java.awt.Font
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import javax.swing.ImageIcon

open class Assets {
    private val images: MutableMap<String, BufferedImage> = mutableMapOf()

    val assetsFolder: String
        get() {
            return "./assets"
        }

    init {
        loadImages(
            "images/background.png",
            "images/bird.png",
            "images/bottom_pipe.png",
            "images/top_pipe.png"
        )
    }

    fun getImage(name: String): BufferedImage? {
        return images[name]
    }

    fun loadImages(vararg imagePaths: String) {
        for (imagePath in imagePaths) {
            loadImage(imagePath)
        }
    }

    fun loadImage(imagePath: String): BufferedImage {
        val file = File("$assetsFolder/$imagePath")
        val image: BufferedImage

        try {
            image = ImageIO.read(file)
        } catch (_: Exception) {
            throw ImageException(imagePath)
        }

        val fileName: String = file.nameWithoutExtension
        images[fileName] = image

        return image
    }

    fun resizeImageIcon(imageIcon: ImageIcon, width: Int, height: Int): ImageIcon {
        val originalImage = imageIcon.image
        val scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        return ImageIcon(scaledImage)
    }

    fun registerFont(fontPath: String, size: Float = 16f) {
        val fontFile = File("$assetsFolder/$fontPath")

        try {
            val font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size)
            val graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment()

            graphicsEnvironment.registerFont(font)
        } catch (e: Exception) {
            throw FontException("$assetsFolder/$fontPath")
        }
    }
}