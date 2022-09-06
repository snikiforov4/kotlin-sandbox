package banner.font

data class Font(val charHeight: Int, private val alphabet: Map<Char, List<String>>) {
    fun byChar(char: Char): List<String> {
        return alphabet[char]!!
    }
}