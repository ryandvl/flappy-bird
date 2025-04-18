package dto

import kotlin.math.max

class Position(var x: Int, var y: Int, var maxX: Int? = null, var maxY: Int? = null) {
    private fun checkMax() {
        maxX?.let { x = max(x, it) }
        maxY?.let { y = max(y, it) }
    }

    fun set(x: Int?, y: Int?) {
        x?.let {
            this.x = x
        }
        y?.let {
            this.y = y
        }
    }

    fun add(deltaX: Int = 0, deltaY: Int = 0): Position {
        x += deltaX
        y += deltaY

        checkMax()

        return this
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}