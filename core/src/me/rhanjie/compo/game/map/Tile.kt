package me.rhanjie.compo.game.map

enum class TileType {
    GRASS, STONE
}

class Tile constructor(tileType: TileType, whichTexture: Int){
    companion object {
        var SIZE = 64
    }

    var type = tileType
    var which = whichTexture
}