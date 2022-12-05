import java.io.File
import java.lang.RuntimeException
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt")
    .readLines()

fun Any.inputFromResources(dayName: String, partName: String) = this::class.java
    .getResource("$dayName/$partName.txt")
    ?.file
    ?.let { File(it) }
    ?.readLines()
    ?: throw RuntimeException("File not found: $dayName/$partName.txt")

fun Any.inputLines(dayName: String, partName: String) = inputFromResources(dayName, partName)

fun Any.inputAsInt(dayName: String, partName: String) = inputFromResources(dayName, partName)
    .map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')