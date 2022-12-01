import java.io.File
import java.lang.RuntimeException
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src/main/resources", "$name.txt")
    .readLines()

fun Any.inputFromResources(name: String) = this::class.java
    .getResource("$name.txt")
    ?.file
    ?.let { File(it) }
    ?.readLines()
    ?: throw RuntimeException("File not found: $name")

fun Any.inputLines(name: String) = inputFromResources(name)

fun Any.inputAsInt(name: String) = inputFromResources(name)
    .map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')