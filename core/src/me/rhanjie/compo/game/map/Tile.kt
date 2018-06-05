package me.rhanjie.compo.game.map

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import me.rhanjie.compo.game.random
import me.rhanjie.compo.game.resources.TexturesManager

enum class TileType {
    GRASS, STONE, APPLE, BRUSH
}

class Tile constructor(var type: TileType, private var number: Int, var collision: Boolean = false)
    : Sprite(TexturesManager.getTexture("${type.name.toLowerCase()}${(0..number).random() + 1}")) {

    companion object {
        var SIZE = 128F
    }

    fun copy(): Tile = Tile(type, number, collision)
}