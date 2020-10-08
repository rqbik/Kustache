package com.rqbik.kustache.serializer.karbon

import com.rqbik.kustache.text.Text

fun Text.asKarbon() = KarbonTextSerializer.serialize(this)