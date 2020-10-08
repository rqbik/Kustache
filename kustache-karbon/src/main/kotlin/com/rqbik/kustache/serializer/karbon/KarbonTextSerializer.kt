package com.rqbik.kustache.serializer.karbon

import com.karbonpowered.commons.Tristate
import com.karbonpowered.text.LiteralText
import com.karbonpowered.text.format.TextColor
import com.karbonpowered.text.format.TextDecorations
import com.rqbik.kustache.serializer.TextSerializer
import com.rqbik.kustache.text.Text
import com.karbonpowered.text.Text as KarbonText

object KarbonTextSerializer : TextSerializer<KarbonText> {
    override fun serialize(text: Text): KarbonText =
        KarbonText(
            *text.components.map {
                LiteralText
                    .builder()
                    .content(it.content)
                    .color(TextColor(it.color.color))
                    .decoration(TextDecorations.BOLD, Tristate.fromNullableBoolean(it.bold))
                    .decoration(TextDecorations.ITALIC, Tristate.fromNullableBoolean(it.italic))
                    .decoration(TextDecorations.STRIKETHROUGH, Tristate.fromNullableBoolean(it.strikethrough))
                    .decoration(TextDecorations.OBFUSCATED, Tristate.fromNullableBoolean(it.obfuscated))
                    .decoration(TextDecorations.UNDERLINED, Tristate.fromNullableBoolean(it.underlined))
                    .build()
            }.toTypedArray()
        )
}