abstract class Days<T>(
    private val expectedPart1Result: T,
    private val expectedPart2Result: T
) {

    abstract fun part1(input: List<String>): T

    abstract fun part2(input: List<String>): T

    fun run() {
        val day = getDayFromClass()
        testPart1(day, expectedPart1Result)
        runPart1(day)
        testPart2(day, expectedPart2Result)
        runPart2(day)
    }

    private fun getDayFromClass() = this::class.simpleName
        ?: throw RuntimeException("No simple name of the class")

    private fun testPart1(dayName: String, expectedResult: T) {
        val testInput1 = inputLines(dayName, "part1-example")
        val part1TestResult = part1(testInput1)
        println("Part1 Test Result: $part1TestResult")
        check(part1TestResult == expectedResult)
    }

    private fun testPart2(dayName: String, expectedResult: T) {
        val testInput2 = inputLines(dayName, "part2-example")
        val part2TestResult = part2(testInput2)
        println("Part2 Test Result: $part2TestResult")
        check(part2TestResult == expectedResult)
    }

    private fun runPart2(dayName: String) {
        val input2 = inputLines(dayName, "part2")
        val part2Result = part2(input2)
        println("Part2 final result: $part2Result")
    }

    private fun runPart1(dayName: String) {
        val input1 = inputLines(dayName, "part1")
        val part1Result = part1(input1)
        println("Part1 final result: $part1Result")
    }
}