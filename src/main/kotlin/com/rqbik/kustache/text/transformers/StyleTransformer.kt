package com.rqbik.kustache.text.transformers

import com.rqbik.kustache.text.Text

enum class StyleTransformer : TextTransformer {
    UNDERLINED,
    BOLD,
    ITALIC,
    OBFUSCATED,
    STRIKETHROUGH;

    override fun transform(text: Text, content: String, name: String) {
        when (valueOf(name)) {
            UNDERLINED -> text.currentComponent.underlined = true
            BOLD -> text.currentComponent.bold = true
            ITALIC -> text.currentComponent.italic = true
            OBFUSCATED -> text.currentComponent.obfuscated = true
            STRIKETHROUGH -> text.currentComponent.strikethrough = true
        }
    }
}