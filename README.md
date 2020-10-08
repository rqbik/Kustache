# {{ Kustache }}

Modern Minecraft string parser & transformer written in Kotlin.

Kustache allows you to use legacy colors and styles along with the new fancy hex colors directly in your localization:

```
{{ RED }}Red text {{ #FFFFFF }}White text {{ BOLD }}White bold text
```

Kustache is built on `transformers` - functions that transform a text component. There are two types of transformers:
- Cascading transformers *(default)* \
  *Applied to every component after them* \
  *Every color and every style is a cascading transformer.*

- Noncascading transformers\
  *Applied only once to the first component after them*

Because of this, creating your own styles is very easy:

```kotlin
// Create a transformer provider
val provider = TransformerProvider.provider.apply {
    // Register a {{ GRADIENT }} noncascading (not repeating) text transformer
    // This will turn our text into a black-to-white gradient.
    // We want to apply it to just the first text component after this transformer, so we make it noncascading.  
    this["GRADIENT"] = NoncascadingTextTransformer { text, content, _ ->
        // Split text in several components, each with a different color, forming a text gradient!
        text.currentComponent.content = content[0].toString()
        content.drop(1).forEachIndexed { idx, it ->
            val percentFade = idx.toFloat() / (content.length - 1).toFloat()

            // Add new components to your text
            text.components.add(TextComponent().apply {
                this.content = it.toString()
                // ...and color each one of them differently
                color = Color(percentFade, percentFade, percentFade)
            })
        }
    }

    // Register a {{ HIGHLIGHT }} text transformer
    // This is an example of a custom style.
    this["HIGHLIGHT"] = TextTransformer { text, _, _ ->
        text.currentComponent.apply {
            color = ColorTransformer.YELLOW.color
            bold = true
            underlined = true
        }
    }
}

// Pass this provider to a parser
val kustache = Kustache(provider)
// Parse it!
val message = kustache.parse("{{ GRADIENT }}Gradient text! {{ HIGHLIGHT }}Highlighted text!")
// `message` is now ready to be sent to a player.
```

Kotlin DSL Dependency:

```
repositories {
    maven { setUrl("https://jitpack.io/") }
}

dependencies {
    implementation("com.github.rqbik", "kustache", "1.0.3")
    // ...and serializers...
    // implementation("com.github.rqbik", "kustache-plain", "1.0.3")
}
```
