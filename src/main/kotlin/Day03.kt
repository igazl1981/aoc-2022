/**
 * [Day3](https://adventofcode.com/2022/day/3)
 */

class Day03 : Days<Int>(157, 70) {

    override fun part1(input: List<String>): Int {

        val values = getItemTypeValues()

        return input
            .map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
            .map { (compartment1, compartment2) -> compartment1.toCharArray().intersect(compartment2.toCharArray().toSet()) }
            .fold(0) { acc, intersect ->
                val first = intersect.first()
                val i = values[first]!!
                println("$first - $i")
                acc + i
            }
    }

    override fun part2(input: List<String>): Int {
        val values = getItemTypeValues()

        return input.windowed(3, 3)
            .map { it.map { it.toCharArray() } }
            .map { it[0].intersect(it[1].intersect(it[2].toSet())) }
            .fold(0) { acc, intersect ->
                val first = intersect.first()
                val i = values[first]!!
                println("$first - $i")
                acc + i
            }

    }

    private fun getItemTypeValues(): Map<Char, Int> {
        val values = mutableMapOf<Char, Int>()
        for (c in 'a'..'z') {
            values[c] = c.code - 96
        }
        for (c in 'A'..'Z') {
            values[c] = c.code - 38
        }
        return values.toMap()
    }

}
