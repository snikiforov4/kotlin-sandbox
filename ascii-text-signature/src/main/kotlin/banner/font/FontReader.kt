package banner.font

import java.io.File
import java.util.concurrent.ConcurrentHashMap

private val delimiterRegexp = "\\s+".toRegex()

sealed interface FontReader {
    fun read(fontMeta: FontMeta): Font
}

object CacheableFontReader : FontReader {
    private val cache = ConcurrentHashMap<String, Font>()

    override fun read(fontMeta: FontMeta): Font = cache.computeIfAbsent(fontMeta.path) {
        readFontFrom(fontMeta)
    }
}

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
