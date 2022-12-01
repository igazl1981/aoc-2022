abstract class Days<T>(
    private val expectedPart1Result: T,
    private val expectedPart2Result: T
) {

    abstract fun part1(input: List<String>): T

    abstract fun part2(input: List<String>): T

    fun run() {
        val dailyFileName = getDailyFileName()
        testPart1(dailyFileName, expectedPart1Result)
        runPart1(dailyFileName)
        testPart2(dailyFileName, expectedPart2Result)
        runPart2(dailyFileName)
    }

    private fun getDailyFileName() = this::class.simpleName
        ?: throw RuntimeException("No simple name of the class")

    private fun testPart1(dailyFileName: String, expectedResult: T) {
        val testInput1 = inputLines("$dailyFileName-part1-example")
        val part1TestResult = part1(testInput1)
        println("Part1 Test Result: $part1TestResult")
        check(part1TestResult == expectedResult)
    }

    private fun testPart2(dailyFileName: String, expectedResult: T) {
        val testInput2 = inputLines("$dailyFileName-part2-example")
        val part2TestResult = part2(testInput2)
        println("Part2 Test Result: $part2TestResult")
        check(part2TestResult == expectedResult)
    }

    private fun runPart2(dailyFileName: String) {
        val input2 = inputLines("$dailyFileName-part2")
        val part2Result = part2(input2)
        println("Part2 final result: $part2Result")
    }

    private fun runPart1(dailyFileName: String) {
        val input1 = inputLines("$dailyFileName-part1")
        val part1Result = part1(input1)
        println("Part1 final result: $part1Result")
    }
}