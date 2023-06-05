/*
 * MIT License
 *
 * Copyright (c) 2023 Jorge Garcia
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

val ides = mutableListOf(
    "IntelliJ IDEA",
    "Eclipse",
    "Visual Studio",
    "Visual Studio Code",
    "PyCharm",
    "Android Studio",
    "NetBeans",
    "Xcode",
    "Atom",
    "Sublime Text"
)

val operativeSystems = mutableSetOf(
    "Windows",
    "Linux",
    "MacOS",
    "Android",
    "iOS",
    "Chrome OS",
    "Solaris",
    "FreeBSD",
    "OpenBSD",
    "AIX",
)

val languages = mapOf(
    "Java" to "java",
    "Python" to "py",
    "Kotlin" to "kt",
    "C#" to "cs",
    "JavaScript" to "js",
    "TypeScript" to "ts",
    "PHP" to "php",
    "C" to "c",
    "C++" to "cpp",
    "Go" to "go",
)

fun startLanguages() {
    ides.add("PhpStorm")
    ides.add("WebStorm")
    ides.add("RubyMine")
    ides.add("CLion")
    ides.add("GoLand")

    println("IDEs:")
    ides.forEach { println("* $it") }
    println("Num. of IDEs: ${ides.size}")

    operativeSystems.add("Windows")
    operativeSystems.add("Linux")
    operativeSystems.add("MacOS")

    println("Operative Systems:")
    operativeSystems.forEach { println("* $it") }
    println("Num. of Operative Systems: ${operativeSystems.size}")

    println("Languages:")
    languages.forEach { (key, value) -> println("* $key: $value") }
    println("Num. of Languages: ${languages.size}")

    println("IDEs that contain the word Studio:")
    ides.filter { it.contains("Studio") }.forEach { println(it) }

    println("Operative Systems name's length:")
    operativeSystems.map { it.length }.forEach { println(it) }

    println("Any Java: ${languages.entries.any { it.key == "Java" }}")
    println("All Java: ${languages.entries.all { it.key == "Java" }}")
    println("None Java: ${languages.entries.none { it.key == "Java" }}")

    languages.values.find { it.startsWith("j") }?.let { println("Extension starting with j: $it") }
    languages.values.findLast { it.startsWith("j") }?.let { println("Extension starting with j: $it") }

    println("First IDE containing Studio: ${ides.first { it.contains("Studio") }}")
    println("Last IDE containing Studio: ${ides.last { it.contains("Studio") }}")

    println("Num. of OS starting with W: ${operativeSystems.count { it.startsWith("W") }}")

    languages.entries.associateBy({ it.key }, { it.value })
        .forEach { (key, value) -> println("The extension for $key is $value") }

    languages.entries.groupBy { it.value.length }.forEach { (key, value) ->
        println("The languages with extension length $key are:")
        value.forEach { println("* ${it.key}") }
    }

    ides.partition { it.contains("Studio") }.let { it ->
        println("IDEs containing Studio:")
        it.first.forEach { println("* $it") }
        println("IDEs not containing Studio:")
        it.second.forEach { println("* $it") }
    }

    val listOfConcepts = listOf(ides, operativeSystems)
    println("List of list of concepts: $listOfConcepts")

    val allConcepts = listOfConcepts.flatten()
    println("List of all concepts: $allConcepts")

    languages.keys.minOrNull()?.let { println("Min. language alphabetically: $it") }
    languages.keys.maxOrNull()?.let { println("Max. language alphabetically: $it") }

    println("All concepts sorted alphabetically: ${allConcepts.sorted()}")

    ides.zip(operativeSystems) { ide, os -> "$ide runs on $os" }.forEach(::println)

    languages.getOrElse("Java") { "Extension Not found" }.let(::println)
    languages.getOrElse("Ruby") { "Extension Not found" }.let(::println)
}

fun main() {
    startLanguages()
}
