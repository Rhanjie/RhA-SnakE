package me.rhanjie.compo.resources

import com.badlogic.gdx.graphics.Texture

class TexturesManager{
    companion object {
        var textures = HashMap<String, Texture>()

        fun load() {
            textures["water"] = Texture("water.png")
            textures["background"] = Texture("background.png")

            textures["grass"] = Texture("grass.png")
            textures["stone"] = Texture("stone.png")

            textures["player"] = Texture("characters/player.png")
            textures["villager"] = Texture("characters/villager.png")

            textures["house"] = Texture("objects/shop.png")
            textures["farmland"] = Texture("objects/farmland.png")

            textures["hud"] = Texture("hud.png")
        }

        fun dispose() {
            for(texture in textures)
                texture.value.dispose()
        }
    }
}