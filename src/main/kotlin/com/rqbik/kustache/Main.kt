package com.rqbik.kustache

import com.rqbik.kustache.text.TextComponent
import com.rqbik.kustache.text.transformers.NoncascadingTextTransformer
import com.rqbik.kustache.text.transformers.TextTransformer
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.java.JavaPlugin
import java.awt.Color

class Main : JavaPlugin(), Listener {
    override fun onEnable() {
        Bukkit.getServer().pluginManager.registerEvents(this, this)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val provider = TransformerProvider.provider.apply {
            this["GRADIENT"] = NoncascadingTextTransformer { text, content, _ ->
                text.currentComponent.content = content[0].toString()
                content.drop(1).forEachIndexed { idx, it ->
                    val percentFade = idx.toFloat() / (content.length - 1).toFloat()

                    text.components.add(TextComponent().apply {
                        this.content = it.toString()
                        color = Color(percentFade, percentFade, percentFade)
                    })
                }
            }

            this["MAIN_COLOR"] = TextTransformer { text, _, _ ->
                text.currentComponent.color = Color(0, 0, 0)
            }
        }

        val kustache = Kustache(provider)
        val toParse = "{{ GREEN }}Green {{ BOLD }}Green bold {{ ITALIC }}Italic {{ GRADIENT }} gradient! {{ WHITE }} abc..."

        val msg = kustache.parse(toParse)
        event.player.sendMessage(msg)
    }
}