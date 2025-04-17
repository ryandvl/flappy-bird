package gfx

import errors.ImageException
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

open class Assets {
    private val loaded: MutableMap<String, BufferedImage> = mutableMapOf()

    private val assetsFolder: String
        get() {
            return "./assets"
        }

    fun getImage(name: String): Image? {
        return loaded[name]
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
        loaded[fileName] = image

        return image
    }
}