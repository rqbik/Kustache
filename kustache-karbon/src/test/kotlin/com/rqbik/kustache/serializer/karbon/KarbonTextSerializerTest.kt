package com.rqbik.kustache.serializer.karbon

import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextColors
import com.karbonpowered.text.format.TextDecorations
import com.rqbik.kustache.Kustache
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.awt.Color
import com.karbonpowered.text.Text as KarbonText

class KarbonTextSerializerTest {
    private fun createKustacheText() = Kustache().parse(
        "{{ RED }}Red text {{ BOLD }}Red bold text {{ ITALIC }}Red bold italic text {{ #FFFFFF }}White text"
    )

    private fun createKarbonText() = KarbonText(
        TextColors.RED, "Red text ",
        TextColors.RED, TextDecorations.BOLD, "Red bold text ",
        TextColors.RED, TextDecorations.BOLD, TextDecorations.ITALIC, "Red bold italic text ",
        TextColor.of(Color.decode("#FFFFFF")), "White text"
    )

    @Test
    fun serialize() {
        val expected = createKarbonText()
        val text = createKustacheText()

        val serialized = text.asKarbon()

        assertComponents(expected, serialized)
    }

    private fun assertComponents(expected: KarbonText, actual: KarbonText) {
        val expectedChildren = expected.children
        val actualChildren = actual.children
        assertArrayEquals(expectedChildren.toTypedArray(), actualChildren.toTypedArray())
    }
}