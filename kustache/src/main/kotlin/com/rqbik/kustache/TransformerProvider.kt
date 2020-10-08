package com.rqbik.kustache

import com.rqbik.kustache.transformers.ColorTransformer
import com.rqbik.kustache.transformers.StyleTransformer
import com.rqbik.kustache.transformers.TextTransformer

object TransformerProvider {
    internal val defaultProvider: Map<String, TextTransformer> = mapOf(
        *ColorTransformer.values().map {
            it.name to it
        }.toTypedArray(),

        *StyleTransformer.values().map {
            it.name to it
        }.toTypedArray(),

        "RESET" to TextTransformer { text, _, _ ->
            text.currentComponent.reset()
        }
    )

    val provider
        get() = defaultProvider.toMutableMap()
}
