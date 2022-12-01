/**
 * [Day1](https://adventofcode.com/2022/day/1)
 */

class Day01 : Days<Int>(24000, 45000) {

    override fun part1(input: List<String>): Int {
        return collectElfCalories(input).max()
    }

    override fun part2(input: List<String>): Int {
        return collectElfCalories(input)
            .sorted()
            .takeLast(3)
            .sum()

    }

    private fun collectElfCalories(input: List<String>): MutableList<Int> {
        val elfCalories = mutableListOf(0)
        return input.fold(elfCalories) { acc, i ->
            if (i.isNotEmpty()) {
                acc[acc.lastIndex] += i.toInt()
            } else {
                acc.add(0)
            }
            acc
        }
    }
}