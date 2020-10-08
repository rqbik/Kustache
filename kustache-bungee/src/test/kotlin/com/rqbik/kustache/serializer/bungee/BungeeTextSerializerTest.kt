package com.rqbik.kustache.serializer.bungee

import com.rqbik.kustache.Kustache
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.TextComponent
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class BungeeTextSerializerTest {
    fun createKustacheText() = Kustache().parse(
        "{{ RED }}Red text {{ BOLD }}Red bold text {{ ITALIC }}Red bold italic text {{ #FFFFFF }}White text"
    )

    fun createBungeeText() = arrayOf(TextComponent("Red text ").apply {
        color = ChatColor.RED
        extra = listOf(
            TextComponent("Red bold text ").apply {
                color = ChatColor.RED
                isBold = true
            },
            TextComponent("Red bold italic text ").apply {
                color = ChatColor.RED
                isBold = true
                isItalic = true
            },
            TextComponent("White text").apply {
                color = ChatColor.WHITE
            }
        )
    })

    @Test
    fun serialize() {
        val expected = createBungeeText()
        val text = createKustacheText()

        val serialized = text.asBungee()

        assertArrayEquals(expected, serialized)
    }
}