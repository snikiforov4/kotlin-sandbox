package banner

import banner.font.Font
import banner.font.getTitleFont
import banner.font.getSubtitleFont
import kotlin.math.max

private const val FRAME_CHAR = "8"
private const val BORDER_WIDTH = 2
private const val CONTENT_EXTRA_SPACE_ON_SIDES = 4

fun main() {
    val (name, status) = readTag()
    val printableTitle = toPrintableForm(name, ::getTitleFont)
    val printableSubtitle = toPrintableForm(status, ::getSubtitleFont)
    val contentLength = max(printableTitle.first().length, printableSubtitle.first().length) +
            CONTENT_EXTRA_SPACE_ON_SIDES
    val borderLine = FRAME_CHAR.repeat(contentLength + 2 * BORDER_WIDTH)
    val nameLines = toPrintableLines(contentLength, printableTitle)
    val statusLines = toPrintableLines(contentLength, printableSubtitle)

    println(borderLine)
    nameLines.forEach(::println)
    statusLines.forEach(::println)
    println(borderLine)
}

data class Tag(val title: String, val subtitle: String)

private fun readTag(): Tag {
    print("Enter title text: ")
    val title = readln().trim()
    print("Enter subtitle line: ")
    val subtitle = readln().trim()
    return Tag(title, subtitle)
}

private fun toPrintableForm(name: String, fontProvider: () -> Font): List<String> {
    val font = fontProvider()
    val nameToPrint = MutableList(font.charHeight) { "" }
    for (c in name) {
        val char = font.byChar(c)
        char.forEachIndexed { i, s ->
            nameToPrint[i] += s
        }
    }
    return nameToPrint.toList()
}

private fun toPrintableLines(
    contentLength: Int,
    printableContent: List<String>
): List<String> {
    val totalPaddingForName = contentLength - printableContent.first().length
    val nameLines = List(printableContent.size) {
        buildString {
            append(FRAME_CHAR.repeat(BORDER_WIDTH))
            append(" ".repeat(totalPaddingForName / 2))
            append(printableContent[it])
            append(" ".repeat((totalPaddingForName + 1) / 2))
            append(FRAME_CHAR.repeat(BORDER_WIDTH))
        }
    }
    return nameLines
}
