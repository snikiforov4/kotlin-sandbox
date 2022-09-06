package signature

import java.io.File

private const val PERSONAL_NAME_FONT_PATH = "roman.txt"
private const val PERSONAL_NAME_FONT_SPACE_LENGTH = 10

private const val STATUS_FONT_PATH = "medium.txt"
private const val STATUS_FONT_SPACE_LENGTH = 5

private val delimiterRegexp = "\\s+".toRegex()

fun getPersonalNameFont(): Font = readFontFrom(FontMeta(PERSONAL_NAME_FONT_PATH, PERSONAL_NAME_FONT_SPACE_LENGTH))

fun getStatusFont(): Font = readFontFrom(FontMeta(STATUS_FONT_PATH, STATUS_FONT_SPACE_LENGTH))

private fun readFontFrom(fontMeta: FontMeta): Font {
    val file = File(ClassLoader.getSystemResource(fontMeta.path).toURI().path)
    val lines = file.readLines()
    check(lines.isNotEmpty()) { "File: $fontMeta.path is empty" }
    val (charHeight, numOfChars) = lines[0].split(delimiterRegexp).map { it.toInt() }
    val alphabet = HashMap<Char, List<String>>()
    repeat(numOfChars) { charIdx ->
        val startIdx = ((charHeight + 1) * charIdx) + 1
        val (charRaw, charWidthRaw) = lines[startIdx].split(delimiterRegexp)
        check(charRaw.length == 1) { "char should be one symbol: $charRaw" }
        val charWidth = charWidthRaw.toInt()
        val block = lines.subList(startIdx + 1, startIdx + 1 + charHeight).toList()
        block.forEach { check(it.length == charWidth) }
        alphabet[charRaw[0]] = block
    }
    val spaceLine = " ".repeat(fontMeta.spaceLength)
    val space = List(charHeight) { spaceLine }
    alphabet[' '] = space
    return Font(charHeight, alphabet)
}

class Font(val charHeight: Int, private val alphabet: Map<Char, List<String>>) {
    fun byChar(char: Char): List<String> {
        return alphabet[char]!!
    }
}

private class FontMeta(val path: String, val spaceLength: Int)