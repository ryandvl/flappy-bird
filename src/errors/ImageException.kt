package errors

class ImageException(path: String) : Exception("Cannot load image from path: $path")