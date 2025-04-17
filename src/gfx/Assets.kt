package gfx

import errors.ImageException
import java.awt.Image
import java.io.File
import javax.swing.ImageIcon

class Assets {
    val loaded: MutableMap<String, Image> = mutableMapOf()

    private val assetsFolder: String
        get() {
            return File("assets").absolutePath
        }

    fun loadImage(assetPath: String) {
        val absolutePath = "$assetsFolder/$assetPath"
        val file = File(absolutePath)

        if (!file.exists() && !file.isFile()) {
            throw ImageException(absolutePath)
        }

        val fileName: String = file.nameWithoutExtension
        loaded[fileName] = ImageIcon(javaClass.getResource(file.path)).image
    }
}