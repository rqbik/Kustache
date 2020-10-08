package com.rqbik.kustache.serializer.plain

import com.rqbik.kustache.Kustache
import net.md_5.bungee.api.ChatColor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KarbonTextSerializerTest {
    private fun createKustacheText() = Kustache().parse(
        "{{ RED }}Red text {{ BOLD }}Red bold text {{ ITALIC }}Red bold italic text {{ #FFFFFF }}White text"
    )

    private fun createPlainText() =
        "${ChatColor.RED}Red text ${ChatColor.RED}${ChatColor.BOLD}Red bold text ${ChatColor.RED}${ChatColor.BOLD}${ChatColor.ITALIC}Red bold italic text ${ChatColor.WHITE}White text"

    @Test
    fun serialize() {
        val expected = createPlainText()
        val text = createKustacheText()

        val serialized = text.asPlain()

        assertEquals(expected, serialized)
    }
}