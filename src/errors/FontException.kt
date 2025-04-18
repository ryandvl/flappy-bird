package errors

class FontException(path: String) : Exception("Cannot load font from path: $path")