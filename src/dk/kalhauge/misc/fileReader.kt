package dk.kalhauge.misc

import java.io.File

fun allWordsOf(path: String, delimiter: String) =
  File(path)
    .readLines()
    .map { line: String -> line.split(delimiter) }
    .flatten()
    .filter { word -> word.isNotBlank() }
    .map { it.toLowerCase() }

fun main(args: Array<String>) {
  allWordsOf("shakespeare.txt", "[^A-Za-z']")
    .take(100)
    .forEach { println(it) }
  }

