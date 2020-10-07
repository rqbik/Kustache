package com.rqbik.kustache.text

import com.rqbik.kustache.text.transformers.ColorTransformer

class TextComponent {
    var underlined = false

    var bold = false

    var italic = false

    var obfuscated = false

    var strikethrough = false

    var color = ColorTransformer.WHITE.color

    var content = ""

    fun reset() {
        underlined = false
        bold = false
        italic = false
        obfuscated = false
        strikethrough = false
        color = ColorTransformer.WHITE.color
    }
}