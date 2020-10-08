package com.rqbik.kustache.text

class Text {
    val components: MutableList<TextComponent> = mutableListOf()

    val currentComponent
        get() = components.last()

    fun append(content: String) = TextComponent(content).also { components.add(it) }
    fun append(content: String, apply: TextComponent.() -> Unit) =
        TextComponent(content).apply(apply).also { components.add(it) }

    override fun toString() = "Text(${components.foldIndexed("") { idx, acc, it -> "$acc${if (idx == 0) "" else ", "}$it" }}"
}