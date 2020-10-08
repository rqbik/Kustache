package com.rqbik.kustache.serializer.plain

import com.rqbik.kustache.serializer.TextSerializer
import com.rqbik.kustache.text.Text
import net.md_5.bungee.api.ChatColor

object PlainTextSerializer : TextSerializer<String> {
    override fun serialize(text: Text): String =
        text.components.fold("") { acc, component ->
            val sb = StringBuilder().apply {
                if (component.color.shortColorCode != null) append("${ChatColor.COLOR_CHAR}${component.color.shortColorCode}")
                else append(ChatColor.of(component.color.color))
                if (component.bold == true) append(ChatColor.BOLD)
                if (component.italic == true) append(ChatColor.ITALIC)
                if (component.obfuscated == true) append(ChatColor.MAGIC)
                if (component.strikethrough == true) append(ChatColor.STRIKETHROUGH)
                if (component.underlined == true) append(ChatColor.UNDERLINE)
                append(component.content)
            }
            "${acc}${sb}"
        }
}