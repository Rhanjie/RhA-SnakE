package me.rhanjie.compo.game.resources

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.Gdx

class TexturesManager{
    companion object {
        var textures = HashMap<String, Texture>()

        init {
            this.load()
        }

        private fun load() {
            val file = Gdx.files.local("data/textures.txt")
            var jsonValues = JsonReader().parse(file.readString())

            println("Loading resources...")
            for(index in (0 until 17)) { //TODO: Remove hardcode
                textures[jsonValues[index].name] = Texture(jsonValues[index].getString("value"))

                println("- [${jsonValues[index].name}] ${jsonValues[index].getString("value")}")
            }
        }

        fun dispose() {
            for(texture in textures)
                texture.value.dispose()
        }
    }
}