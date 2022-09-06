package signature

import kotlin.math.max

private const val FRAME = "8"
private const val BORDER_WIDTH = 2
private const val CONTENT_EXTRA_SPACE_ON_SIDES = 4

fun main() {
    val (name, status) = readTag()
    val printableName = toPrintableForm(name, ::getPersonalNameFont)
    val printableStatus = toPrintableForm(status, ::getStatusFont)
    val contentLength = max(printableName.first().length, printableStatus.first().length) +
            CONTENT_EXTRA_SPACE_ON_SIDES
    val borderLine = FRAME.repeat(contentLength + 2 * BORDER_WIDTH)
    val nameLines = toPrintableLines(contentLength, printableName)
    val statusLines = toPrintableLines(contentLength, printableStatus)

    println(borderLine)
    nameLines.forEach(::println)
    statusLines.forEach(::println)
    println(borderLine)
}

private fun readTag(): Tag {
    print("Enter name and surname: ")
    val name = readln().trim()
    print("Enter person's status: ")
    val status = readln().trim()
    return Tag(name, status)
}

data class Tag(val name: String, val status: String)

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
            append(FRAME.repeat(BORDER_WIDTH))
            append(" ".repeat(totalPaddingForName / 2))
            append(printableContent[it])
            append(" ".repeat((totalPaddingForName + 1) / 2))
            append(FRAME.repeat(BORDER_WIDTH))
        }
    }
    return nameLines
}
