package com.rqbik.kustache

import com.rqbik.kustache.text.Text
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

fun CommandSender.sendMessage(text: Text) = sendMessage(text.asBungee())
fun Player.sendActionBar(text: Text) = sendActionBar(text.asBungee())
fun Player.sendTitle(title: Text, subtitle: Text, fadeIn: Int, stay: Int, fadeOut: Int) =
    sendTitle(title.asBungee(), subtitle.asBungee(), fadeIn, stay, fadeOut)
fun Player.setDisplayName(displayName: Text?) = setDisplayName(displayName?.asBungee())
fun Player.setPlayerListHeaderFooter(header: Text?, footer: Text?) =
    setPlayerListHeaderFooter(header?.asBungee(), footer?.asBungee())
fun Player.setPlayerListName(name: Text?) = setPlayerListName(name?.asBungee())
fun Player.setCustomName(name: Text?) { customName = name?.asBungee() }