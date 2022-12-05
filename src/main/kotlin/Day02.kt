import Result.DRAW
import Result.LOSE
import Result.WIN

/**
 * [Day2](https://adventofcode.com/2022/day/2)
 */

class Day02 : Days<Int>(15, 12) {

    override fun part1(input: List<String>): Int {
        return input.map { it.split(" ") }
            .map { Shape.findElfShape(it[0][0]) to Shape.findHumanShape(it[1][0]) }
            .fold(0) { acc, (elfShape, humanShape) ->
                val elfShapeBeats = Shape.findElfShape(elfShape.beats)
                val elfShapeBeatenBy = Shape.findElfShape(elfShapeBeats.beats)
                acc + humanShape.humanValue + when {
                    elfShapeBeats == humanShape -> LOSE.score
                    elfShapeBeatenBy == humanShape -> WIN.score
                    else -> DRAW.score
                }
            }
    }

    override fun part2(input: List<String>): Int {
        return input.map { it.split(" ") }
            .map { Shape.findElfShape(it[0][0]) to Result.findExpectedResult(it[1][0]) }
            .fold(0) { acc, (elfShape, expectedResult) ->
                val chosenHumanShape = when (expectedResult) {
                    LOSE -> Shape.findElfShape(elfShape.beats)
                    WIN -> Shape.findElfShapeBeater(elfShape.elfCode)
                    else -> elfShape
                }

                acc + expectedResult.score + chosenHumanShape.humanValue
            }

    }

}


enum class Shape(val elfCode: Char, val humanCode: Char, val humanValue: Int, val beats: Char) {
    ROCK('A', 'X', 1, 'C'),
    PAPER('B', 'Y', 2, 'A'),
    SCISSOR('C', 'Z', 3, 'B');

    companion object {
        fun findElfShape(code: Char) = values().first { it.elfCode == code }

        fun findElfShapeBeater(code: Char) = values().first { it.beats == code }

        fun findHumanShape(code: Char) = values().first { it.humanCode == code }
    }
}

enum class Result(val score: Int, val code: Char) {
    LOSE(0, 'X'),
    DRAW(3, 'Y'),
    WIN(6, 'Z');

    companion object {
        fun findExpectedResult(code: Char) = values().first { it.code == code }
    }

}
