package com.rqbik.kustache.text

import com.rqbik.kustache.transformers.ColorTransformer

class TextComponent(var content: String = "") {
    var underlined: Boolean? = null

    var bold: Boolean? = null

    var italic: Boolean? = null

    var obfuscated: Boolean? = null

    var strikethrough: Boolean? = null

    var color = ColorTransformer.WHITE.color

    fun reset() {
        underlined = null
        bold = null
        italic = null
        obfuscated = null
        strikethrough = null
        color = ColorTransformer.WHITE.color
    }
    
    override fun toString(): String {
        val sb = StringBuilder("TextComponent('$content'")
        sb.append(", color=${color.color}")
        if (bold == true) sb.append(", bold=true")
        if (italic == true) sb.append(", italic=true")
        if (underlined == true) sb.append(", underlined=true")
        if (obfuscated == true) sb.append(", obfuscated=true")
        if (strikethrough == true) sb.append(", strikethrough=true")
        sb.append(")")
        return sb.toString()
    }
}