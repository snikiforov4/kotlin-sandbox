package banner.font

private const val PERSONAL_NAME_FONT_PATH = "roman.txt"
private const val PERSONAL_NAME_FONT_SPACE_LENGTH = 10

private const val STATUS_FONT_PATH = "medium.txt"
private const val STATUS_FONT_SPACE_LENGTH = 5

private val titleFontMeta = FontMeta(PERSONAL_NAME_FONT_PATH, PERSONAL_NAME_FONT_SPACE_LENGTH)
private val subtitleFontMeta = FontMeta(STATUS_FONT_PATH, STATUS_FONT_SPACE_LENGTH)

private val fontReader = CacheableFontReader

fun getTitleFont(): Font = fontReader.read(titleFontMeta)

fun getSubtitleFont(): Font = fontReader.read(subtitleFontMeta)