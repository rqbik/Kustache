package com.rqbik.kustache.text.transformers

import com.rqbik.kustache.text.Text
import java.awt.Color

enum class ColorTransformer(val color: Color) : TextTransformer {
    BLACK(Color(0x000000)),

    DARK_BLUE(Color(0x0000AA)),

    DARK_GREEN(Color(0x00AA00)),

    DARK_AQUA(Color(0x00AAAA)),

    DARK_RED(Color(0xAA0000)),

    DARK_PURPLE(Color(0xAA00AA)),

    GOLD(Color(0xFFAA00)),

    GRAY(Color(0xAAAAAA)),

    DARK_GRAY(Color(0x555555)),

    BLUE(Color(0x05555FF)),

    GREEN(Color(0x55FF55)),

    AQUA(Color(0x55FFFF)),

    RED(Color(0xFF5555)),

    LIGHT_PURPLE(Color(0xFF55FF)),

    YELLOW(Color(0xFFFF55)),

    WHITE(Color(0xFFFFFF));

    override fun transform(text: Text, content: String, name: String) {
        text.currentComponent.apply {
            reset()
            color = valueOf(name).color
        }
    }
}