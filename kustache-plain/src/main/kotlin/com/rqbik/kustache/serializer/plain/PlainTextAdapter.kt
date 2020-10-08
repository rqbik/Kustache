package com.rqbik.kustache.serializer.plain

import com.rqbik.kustache.text.Text
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun Text.asPlain() = PlainTextSerializer.serialize(this)

fun CommandSender.sendMessage(text: Text) = sendMessage(text.asPlain())
fun Player.sendActionBar(text: Text) = sendActionBar(text.asPlain())
fun Player.sendTitle(title: Text, subtitle: Text, fadeIn: Int, stay: Int, fadeOut: Int) =
    sendTitle(title.asPlain(), subtitle.asPlain(), fadeIn, stay, fadeOut)
fun Player.setDisplayName(displayName: Text?) = setDisplayName(displayName?.asPlain())
fun Player.setPlayerListHeaderFooter(header: Text?, footer: Text?) =
    setPlayerListHeaderFooter(header?.asPlain(), footer?.asPlain())
fun Player.setPlayerListName(name: Text?) = setPlayerListName(name?.asPlain())
fun Player.setCustomName(name: Text?) { customName = name?.asPlain() }