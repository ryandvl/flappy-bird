package dto

data class Vector2D(var x: Int, var y: Int) {
    override fun toString(): String {
        return "($x, $y)"
    }
}