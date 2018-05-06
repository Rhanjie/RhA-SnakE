package me.rhanjie.compo.game.map

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

enum class TileType {
    GRASS, STONE, APPLE, BRUSH
}

class Tile constructor(tileType: TileType, texture: Texture): Sprite(texture) {
    companion object {
        var SIZE = 128F
    }

    var type = tileType
}