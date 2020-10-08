package com.rqbik.kustache

import com.rqbik.kustache.text.Text
import com.rqbik.kustache.text.TextColor
import com.rqbik.kustache.transformers.NoncascadingTextTransformer
import com.rqbik.kustache.transformers.TextTransformer

class Kustache(private val provider: Map<String, TextTransformer> = TransformerProvider.defaultProvider) {
    private val regex = "\\{\\{(.*?)\\}\\}".toRegex()

    fun parse(string: String) = transform(parseString(string))

    private fun parseString(string: String): List<RawTextComponent> {
        val matches = regex.findAll(string).toList()
        val rawComponents: MutableList<RawTextComponent> = mutableListOf()

        // Return unformatted text if there are no transformer groups
        if (matches.isEmpty()) {
            return rawComponents.apply {
                add(RawTextComponent(string, mutableListOf()))
            }
        }

        matches.forEachIndexed { idx, it ->
            //
            // Converting transformer groups to raw text components
            //
            // [...] {{ PREVIOUS? }} [...] {{ CURRENT }} [...] {{ NEXT? }} [...]
            //             ^                     ^     ^       ^    ^
            //             |                     |     |       |    |
            //          Previous              Current  |       |   Next
            //         transformer          transformer|       |transformer
            //        (may be null)                    |       (may be null)
            //                                         |       |
            //                                         +———————+
            //                                          Content
            //                                     (without brackets)
            //
            // Every* transformer creates a raw text component with a content and a set of transformers applied to it.
            // Content is a string between the end of current transformer group and the start of next transformer group.
            //
            // * - if previous raw text component is empty then transformer does not create a new component,
            //     but merges current content with previous component's content.
            //

            // Create first parsed component manually
            if (idx == 0) rawComponents.add(
                RawTextComponent(string.substring(0, it.groups.first()!!.range.first), mutableListOf())
            )

            // Current transformer group
            val current = it.groups.first()!!
            // Current transformer identifier
            val transformer = it.groups.last()!!.value.trim()
            // Previous component
            val previous = rawComponents.last()

            // Adds new component
            fun addComponent(content: String, transformer: String) {
                // If previous component is empty then we merge it with current one
                if (previous.content.isEmpty()) {
                    previous.content += content
                    previous.transformers.add(transformer)
                    // Else create a new component
                } else rawComponents.add(
                    RawTextComponent(
                        content,
                        // Cascading transformers
                        mutableListOf(*previous.transformers.toTypedArray(), transformer)
                    )
                )
            }

            // If this group isn't last
            if (matches.size != idx + 1) {
                // Next transformer group
                val next = matches[idx + 1].groups.first()!!
                // Get content after current group end until the beginning of next group
                val content = string.substring((current.range.last + 1) until next.range.first)
                addComponent(content, transformer)
            } else {
                // Get content after current group end until the end of the string
                val content = string.substring((current.range.last + 1) until string.length)
                addComponent(content, transformer)
            }
        }

        return rawComponents
    }

    private fun transform(rawComponents: List<RawTextComponent>): Text {
        val text = Text()

        // Filter empty components
        rawComponents.filter { it.content.isNotEmpty() }.forEach { component ->
            // Add new component
            text.append(component.content)

            // Trigger every transformer assigned to this component
            component.transformers.forEachIndexed { idx, it ->
                val transformer = provider[it]

                // If transformer by this identifier exists
                if (transformer != null) {
                    if (
                        // If transformer is...
                        // - cascading or
                        // - non cascading and it's last in component list
                        // ...apply it
                        (transformer is NoncascadingTextTransformer && idx == component.transformers.size - 1)
                        || transformer !is NoncascadingTextTransformer
                    ) transformer.transform(text, component.content, it)
                } else {
                    // If identifier starts with '#' (is a hex color code)
                    if (it.startsWith('#')) {
                        try {
                            // Try to decode it
                            val color = TextColor.of(it)
                            text.currentComponent.reset()
                            text.currentComponent.color = color
                        } catch (e: Exception) {
                            throw KustacheParsingError("Invalid hex color code: \"$it\".")
                        }
                    } else throw KustacheParsingError(
                        "Unknown transformer identifier: \"$it\". Perhaps you missed a closing bracket?"
                    )
                }
            }
        }

        // Return parsed text
        return text
    }

    internal data class RawTextComponent(var content: String, val transformers: MutableList<String>)

    internal class KustacheParsingError(message: String) : Exception(message)
}