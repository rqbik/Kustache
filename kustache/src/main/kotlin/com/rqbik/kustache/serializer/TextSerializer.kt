package com.rqbik.kustache.serializer

import com.rqbik.kustache.text.Text

interface TextSerializer<S> {
    fun serialize(text: Text): S
}