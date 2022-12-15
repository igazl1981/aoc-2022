/**
 * [Day7](https://adventofcode.com/2022/day/7)
 */

class Day07 : Days<Long>(95437, 24933642) {

    private val listingRegex = "([^ ]+) (.*)".toRegex()

    var total = 0L

    var availableTotalSpace = 70000000L
    var requiredSpace = 30000000L

    override fun part1(input: List<String>): Long {
        total = 0L
        val root = dir("/")
        buildDirs(root, input)

        printDirs(root, 0)
        calculateDirSizes(root)
        return total
    }

    var smallestSizeToDelete = 0L
    var missingFreeSpace = 0L

    override fun part2(input: List<String>): Long {
        total = 0L
        val root = dir("/")
        buildDirs(root, input)
//        printDirs(root, 0)
        val totalUsedSpace = calculateDirSizes(root)
        val availableSpace = availableTotalSpace - totalUsedSpace
        missingFreeSpace = requiredSpace - availableSpace
        smallestSizeToDelete = 0L
        calculateDirSizes(root)
        return smallestSizeToDelete
    }

    private fun buildDirs(root: dir, input: List<String>) {
        var currentDir = root
        input.drop(1)
            .forEach { command ->
                if (isListing(command)) {
                    // skip
                } else if (isStepOut(command)) {
                    currentDir = currentDir.parent ?: throw RuntimeException("How it could happen?")
                } else if (isStepIn(command)) {
                    val dirName = command.drop(5)
                    val newDir = dir(dirName).apply { parent = currentDir }
                    currentDir.dirs.add(newDir)
                    currentDir = newDir
                } else {
                    // some listed file/dir
                    val (size, name) = listingRegex.find(command)!!.destructured
                    if (size == "dir") {
                        //skip
                    } else {
                        currentDir.totalSize += size.toLong()
                        currentDir.files.add(file(size.toLong(), name))
                    }
                }

            }
    }

    private fun printDirs(root: dir, level: Int) {
        println(" ".repeat(level * 2) + "d ${root.name} (dir)")
        root.dirs.forEach { printDirs(it, level + 1) }
        root.files.forEach { println(" ".repeat((level + 1) * 2) + "f ${it.name} (${it.size})") }
    }

    private fun calculateDirSizes(root: dir): Long {
         val dirSizes = root
            .dirs
            .sumOf {
                calculateDirSizes(it)
            }
        val totalSize = (dirSizes + root.totalSize)
        if (totalSize <= 100000) {
            total += totalSize
        }
        if (totalSize >= missingFreeSpace && (smallestSizeToDelete == 0L || smallestSizeToDelete > totalSize)) {
            smallestSizeToDelete = totalSize
        }
        return totalSize
    }

    private fun isStepIn(command: String) = command.startsWith("$ cd ")

    private fun isStepOut(command: String) = command == "$ cd .."

    private fun isListing(command: String) = command == "$ ls"

}

data class dir(
    val name: String,
    val dirs: MutableList<dir> = mutableListOf(),
    val files: MutableList<file> = mutableListOf(),
) {
    var parent: dir? = null
    var totalSize: Long = 0L
}

data class file(val size: Long, val name: String)
