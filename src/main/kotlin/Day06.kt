/**
 * [Day6](https://adventofcode.com/2022/day/6)
 */

class Day06 : Days<Int>(7, 19) {

    override fun part1(input: List<String>): Int {
        return getMarker(input, 4)
    }

    override fun part2(input: List<String>): Int {
        return getMarker(input, 14)
    }

    private fun getMarker(input: List<String>, batchSize: Int): Int {
        val processedBatches = input[0].windowed(batchSize)
            .takeWhile {
                it.toCharArray().distinct().size != batchSize
            }
        return processedBatches.size + batchSize
    }

}
