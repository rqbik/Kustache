package com.rqbik.kustache.transformers

import com.rqbik.kustache.text.Text

fun interface NoncascadingTextTransformer : TextTransformer {
    override fun transform(text: Text, content: String, name: String)
}