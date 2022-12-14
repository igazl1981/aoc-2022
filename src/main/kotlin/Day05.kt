import java.util.Stack

/**
 * [Day5](https://adventofcode.com/2022/day/5)
 */

class Day05 : Days<String>("CMZ", "MCD") {

    override fun part1(input: List<String>): String {

        val stacks = initializeStacks(input)

        collectCommands(input).forEach { (amount, from, to) ->
                for (i in 1 until amount + 1) {
                    stacks[to].push(stacks[from].pop())
                }
            }

        return collectTopItems(stacks)
    }

    override fun part2(input: List<String>): String {
        val stacks = initializeStacks(input)

        collectCommands(input).forEach { (amount, from, to) ->
            val map = (1 until amount + 1).map { stacks[from].pop() }.asReversed()
            stacks[to].addAll(map)
        }

        return collectTopItems(stacks)
    }

    private fun collectCommands(input: List<String>) = input.dropWhile { it.isNotEmpty() }
        .mapNotNull { action ->
            action
                .takeIf { it.isNotEmpty() }
                ?.let {
                    val (amount, from, to) = "move (\\d+) from (\\d+) to (\\d+)".toRegex()
                        .find(it)?.destructured!!

                    Triple(amount.toInt(), from.toInt(), to.toInt())
                }

        }

    private fun initializeStacks(input: List<String>): MutableList<Stack<Char>> {
        val initialStacks = input.takeWhile { it.isNotEmpty() }.toList()
        val stacks = MutableList(10) { Stack<Char>() }
        initialStacks
            .takeWhile { it.contains("[") }
            .forEach { line ->
                line
                    .windowed(3, 4)
                    .forEachIndexed { i, crateString ->
                        crateString
                            .replace("[\\[\\] ]".toRegex(), "")
                            .takeIf { crateName -> crateName.isNotEmpty() }
                            ?.also { stacks[i + 1].push(it[0]) }

                    }
            }
        stacks.forEach { it.reverse() }
        return stacks
    }

    private fun collectTopItems(stacks: MutableList<Stack<Char>>) = stacks.filter { it.isNotEmpty() }
        .map {
            it.peek()
        }
        .joinToString("")

}
