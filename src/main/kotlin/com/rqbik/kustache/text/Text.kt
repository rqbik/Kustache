package com.rqbik.kustache.text

import net.md_5.bungee.api.ChatColor
import org.bukkit.entity.Player

class Text {
    val components: MutableList<TextComponent> = mutableListOf()

    val currentComponent
        get() = components.last()

    fun asBungee(): String {
        return components.fold("") { acc, component ->
            var content = "${ChatColor.of(component.color)}"
            if (component.bold) content += ChatColor.BOLD
            if (component.italic) content += ChatColor.ITALIC
            if (component.obfuscated) content += ChatColor.MAGIC
            if (component.strikethrough) content += ChatColor.STRIKETHROUGH
            if (component.underlined) content += ChatColor.UNDERLINE
            content += component.content
            "${acc}${content}"
        }
    }

    companion object Extensions {
        fun Player.sendMessage(text: Text) {
            sendMessage(text.asBungee())
        }
    }
}