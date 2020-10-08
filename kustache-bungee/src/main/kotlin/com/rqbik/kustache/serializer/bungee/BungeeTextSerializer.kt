package com.rqbik.kustache.serializer.bungee

import com.rqbik.kustache.serializer.TextSerializer
import com.rqbik.kustache.text.Text
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.BaseComponent
import net.md_5.bungee.api.chat.TextComponent

object BungeeTextSerializer : TextSerializer<Array<out BaseComponent>> {
    override fun serialize(text: Text): Array<out BaseComponent> =
        arrayOf(
            with(text.components[0]) with@ {
                TextComponent(content).apply {
                    val shortColorCode = this@with.color.shortColorCode

                    color = if (shortColorCode != null)
                        ChatColor.getByChar(shortColorCode)
                    else ChatColor.of(this@with.color.color)

                    if (bold == true) isBold = true
                    if (italic == true) isItalic = true
                    if (strikethrough == true) isStrikethrough = true
                    if (underlined == true) isUnderlined = true
                    if (obfuscated == true) isObfuscated = true

                    extra = text.components.drop(1).map {
                        TextComponent(it.content).apply {
                            val extraShortColorCode = it.color.shortColorCode

                            color = if (extraShortColorCode != null) {
                                ChatColor.getByChar(extraShortColorCode)
                            } else ChatColor.of(it.color.color)

                            if (it.bold == true) isBold = true
                            if (it.italic == true) isItalic = true
                            if (it.strikethrough == true) isStrikethrough = true
                            if (it.underlined == true) isUnderlined = true
                            if (it.obfuscated == true) isObfuscated = true
                        }
                    }
                }
            }
        )
}