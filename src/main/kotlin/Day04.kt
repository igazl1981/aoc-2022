/**
 * [Day4](https://adventofcode.com/2022/day/4)
 */

class Day04 : Days<Int>(2, 4) {

    override fun part1(input: List<String>): Int {
        val sectionAssignments = input.asSequence().map { line ->
            line.split(",")
                .map { it.split("-") }
                .map { IntRange(it[0].toInt(), it[1].toInt()) }
        }.toList()
        return sectionAssignments.count { section ->
            (section[0].first >= section[1].first && section[0].last <= section[1].last)
                    || (section[1].first >= section[0].first && section[1].last <= section[0].last)
        }
    }

    override fun part2(input: List<String>): Int {
        return input.asSequence().map { line ->
            line.split(",").asSequence()
                .map { it.split("-") }
                .map { IntRange(it[0].toInt(), it[1].toInt()) }
                .toList()
        }
            .count { assignments ->
                assignments[0].intersect(assignments[1]).isNotEmpty()
            }
    }

}
