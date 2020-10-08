package com.rqbik.kustache.serializer.bungee

import com.rqbik.kustache.text.Text

fun Text.asBungee() = BungeeTextSerializer.serialize(this)