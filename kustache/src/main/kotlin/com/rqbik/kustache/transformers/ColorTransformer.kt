package com.rqbik.kustache.transformers

import com.rqbik.kustache.text.Text
import com.rqbik.kustache.text.TextColor

enum class ColorTransformer(val color: TextColor) : TextTransformer {
    BLACK(TextColor.of(0x000000)),

    DARK_BLUE(TextColor.of(0x0000AA)),

    DARK_GREEN(TextColor.of(0x00AA00)),

    DARK_AQUA(TextColor.of(0x00AAAA)),

    DARK_RED(TextColor.of(0xAA0000)),

    DARK_PURPLE(TextColor.of(0xAA00AA)),

    GOLD(TextColor.of(0xFFAA00)),

    GRAY(TextColor.of(0xAAAAAA)),

    DARK_GRAY(TextColor.of(0x555555)),

    BLUE(TextColor.of(0x05555FF)),

    GREEN(TextColor.of(0x55FF55)),

    AQUA(TextColor.of(0x55FFFF)),

    RED(TextColor.of(0xFF5555)),

    LIGHT_PURPLE(TextColor.of(0xFF55FF)),

    YELLOW(TextColor.of(0xFFFF55)),

    WHITE(TextColor.of(0xFFFFFF));

    override fun transform(text: Text, content: String, name: String) {
        text.currentComponent.apply {
            reset()
            color = valueOf(name).color
        }
    }
}