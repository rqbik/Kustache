package com.rqbik.kustache.text

import java.awt.Color

class TextColor(val color: Color) {
    val shortColorCode = when (color.toInt()) {
        0x000000 -> '0'
        0x0000AA -> '1'
        0x00AA00 -> '2'
        0x00AAAA -> '3'
        0xAA0000 -> '4'
        0xAA00AA -> '5'
        0xFFAA00 -> '6'
        0xAAAAAA -> '7'
        0x555555 -> '8'
        0x05555FF -> '9'
        0x55FF55 -> 'a'
        0x55FFFF -> 'b'
        0xFF5555 -> 'c'
        0xFF55FF -> 'd'
        0xFFFF55 -> 'e'
        0xFFFFFF -> 'f'
        else -> null
    }

    companion object Factory {
        fun of(color: String) = TextColor(Color.decode(color))
        fun of(color: Int) = TextColor(Color(color))

        private fun Color.toInt(): Int {
            val r = (red shl 16) and 0xFF0000
            val g = (green shl 8) and 0x00FF00
            val b = blue and 0x0000FF

            return 0x000000 or r or g or b
        }
    }
}