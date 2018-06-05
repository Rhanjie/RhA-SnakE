package me.rhanjie.compo.game.resources

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.Gdx

class TexturesManager{
    companion object {
        private var textures = HashMap<String, Texture>()

        init {
            this.load()
        }

        private fun load() {
            val file = Gdx.files.local("data/textures.txt")
            var jsonValues = JsonReader().parse(file.readString())

            println("Loading resources...")
            for(index in (0 until 18)) { //TODO: Get size from file
                textures[jsonValues[index].name] = Texture(jsonValues[index].getString("value"))

                println("- [${jsonValues[index].name}] ${jsonValues[index].getString("value")}")
            }
        }

        fun dispose() {
            for(texture in textures)
                texture.value.dispose()
        }

        fun getTexture(id: String): Texture{
            if(textures.containsKey(id) == false)
                return textures["textureNull"]!!

            else return textures[id]!!
        }
    }
}