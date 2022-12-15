import kotlin.math.max

/**
 * [Day8](https://adventofcode.com/2022/day/8)
 */

class Day08 : Days<Long>(21, 8) {

    override fun part1(input: List<String>): Long {
        val matrix = input.map { line ->
            line.map { it.digitToInt() }
        }
        val width = input[0].length
        val height = input.size
        var counter = 0L
        for(i in 0 until height) {
            for (j in 0 until width) {
                print(matrix[i][j])
                if (i>0 && j>0 && i < height - 1 && j < width - 1) {
                    val hiddenFromTop = (i - 1 downTo 0)
                        .firstOrNull { top -> matrix[top][j] >= matrix[i][j] }
                        ?.let { true }
                        ?: false

                    val hiddenFromLeft = (j-1 downTo 0)
                        .firstOrNull { left -> matrix[i][left] >= matrix[i][j] }
                        ?.let {true}
                        ?: false
                    val hiddenFromRight = (j + 1 until  width)
                        .firstOrNull { right -> matrix[i][right] >= matrix[i][j] }
                        ?.let {true}
                        ?: false
                    val hiddenFromBottom = (i + 1 until height)
                        .firstOrNull { bottom -> matrix[bottom][j] >= matrix[i][j] }
                        ?.let {true}
                        ?: false
                    if (hiddenFromTop && hiddenFromLeft && hiddenFromRight && hiddenFromBottom) {
                        counter++
                    }
                }
            }
            println()
        }







        return width * height - counter
    }

    override fun part2(input: List<String>): Long {
        val matrix = input.map { line ->
            line.map { it.digitToInt() }
        }
        val width = input[0].length
        val height = input.size
        var maxScenicScore = 0L
        for(i in 0 until height) {
            for (j in 0 until width) {
                print(matrix[i][j])
                if (i>0 && j>0 && i < height - 1 && j < width - 1)
                {
                    val topDistance = (i - 1 downTo 0)
                        .firstOrNull { top -> matrix[top][j] >= matrix[i][j] }
                        ?.let { i - it }
                        ?: (i)
                    val leftDistance = (j-1 downTo 0)
                        .firstOrNull { left -> matrix[i][left] >= matrix[i][j] }
                        ?.let { j - it}
                        ?: (j)
                    val rightDistance = (j + 1 until  width)
                        .firstOrNull { right -> matrix[i][right] >= matrix[i][j] }
                        ?.let { it - j }
                        ?: (width - j - 1)
                    val bottomDistance = (i + 1 until height)
                        .firstOrNull { bottom -> matrix[bottom][j] >= matrix[i][j] }
                        ?.let {it - i}
                        ?: (height - i - 1)
                    val currentScenicScore = topDistance.toLong()*leftDistance.toLong()*rightDistance.toLong()*bottomDistance.toLong()
                    maxScenicScore = max(maxScenicScore, currentScenicScore)
                }
            }
            println()
        }
        return maxScenicScore
    }

}
