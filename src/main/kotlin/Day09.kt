import kotlin.math.abs
import kotlin.math.max

/**
 * [Day9](https://adventofcode.com/2022/day/9)
 */

class Day09 : Days<Long>(13, 36) {

    override fun part1(input: List<String>): Long {

        val tailPositions = mutableSetOf<Position>()
        var head = Position(0, 0)
        var tail = Position(0, 0)
        tailPositions.add(tail)
        val commands = getCommands(input)

        commands.forEach { command ->

            (1..command.length).forEach {

                head = command.direction.update(head)
                tail = position(head, tail)
                tailPositions.add(tail)
                println("H: $head - T: $tail")
            }
        }

        return tailPositions.size.toLong()
    }

    override fun part2(input: List<String>): Long {

        val tailPositions = mutableSetOf<Position>()
        var rope = MutableList(10) { Position(0, 0) }
        tailPositions.add(rope[8])
        val commands = getCommands(input)

        commands.forEach { command ->

            (1..command.length).forEach {
                rope[0] = command.direction.update(rope[0])
                rope.forEachIndexed { index, ropeItem ->
                    if (index > 0) {
                        rope[index] = position(rope[index - 1], ropeItem)
                    }
                }
                tailPositions.add(rope[9])
            }
        }

        return tailPositions.size.toLong()
    }

    private fun getCommands(input: List<String>): List<Command> {
        val commands = input.map {
            it.split(" ")
        }.map {
            Command(Direction.getByCode(it[0]), it[1].toInt())
        }
        return commands
    }

    private fun position(head: Position, tail: Position): Position {
        return if (head.notTouching(tail)) {
            val distanceX = head.x - tail.x
            val signX = distanceX / max(abs(distanceX), 1)
            val distanceY = head.y - tail.y
            val signY = distanceY / max(abs(distanceY), 1)

            val moveX = (distanceX + (signX * -1)).takeIf { it != 0 } ?: signX
            val moveY = (distanceY + (signY * -1)).takeIf { it != 0 } ?: signY

            Position(tail.x + moveX, tail.y + moveY)
        } else {
            tail
        }
    }


    data class Position(val x: Int, val y: Int) {
        fun notTouching(tail: Position): Boolean {
            return max(abs(x - tail.x), abs(y - tail.y)) > 1
        }
    }

    data class Command(val direction: Direction, val length: Int)

    enum class Direction(val code: String, val update: (Position) -> Position) {
        UP("U", { it.copy(y = it.y + 1) }),
        DOWN("D", { it.copy(y = it.y - 1) }),
        LEFT("L", { it.copy(x = it.x - 1) }),
        RIGHT("R", { it.copy(x = it.x + 1) });

        companion object {
            fun getByCode(code: String) = values().first { it.code == code }
        }
    }
}
