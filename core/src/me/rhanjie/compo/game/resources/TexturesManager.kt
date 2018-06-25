package me.rhanjie.compo.game.resources

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureRegion
import me.rhanjie.compo.game.map.tiles.Tile

class TexturesManager{
    companion object {
        //private var textures = HashMap<String, Texture>()
        private var textures = HashMap<String, TextureRegion>()

        init {
            this.load()
        }

        private fun load() {
            val file = Gdx.files.local("data/textures.txt")
            val jsonValues = JsonReader().parse(file.readString())
            var index = 0

            println("Loading resources...")
            for(i in (0 until 11)) { //TODO: Get size from file
                val texture = Texture(jsonValues[i].getString("value"))

                for(y in (0 until texture.textureData.height / Tile.SIZE.toInt())){
                    for(x in (0 until texture.textureData.width / Tile.SIZE.toInt())){
                        val textureRegion = TextureRegion(Texture(jsonValues[i].getString("value")), Tile.SIZE.toInt() * x, Tile.SIZE.toInt() * y, Tile.SIZE.toInt(), Tile.SIZE.toInt())
                            textures[jsonValues[i].name+"${++index}"] = textureRegion

                        Gdx.app.debug("Tile Loading", "[${jsonValues[i].name + index}] ${jsonValues[i].getString("value")} [${Tile.SIZE.toInt() * x}:${Tile.SIZE.toInt() * y}]")
                    }
                }

                index = 0
            }
        }

        fun dispose() {
            for(texture in textures);
                //texture.value.dispose()
        }

        fun getTexture(id: String): TextureRegion{
            if(textures.containsKey(id) == false)
                return textures["textureNull1"]!!

            else return textures[id]!!
        }
    }
}