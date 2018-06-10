package me.rhanjie.compo.game.map.tiles

class TileManager {
    private var tiles = HashMap<TileType, Tile>()

    init {
        this.load()
    }

    fun getCopy(tileType: TileType): Tile {
        if (tiles.containsKey(tileType) == false)
            return tiles[TileType.GRASS]!!.copy()

        else return tiles[tileType]!!.copy()
    }

    private fun load(){
        tiles[TileType.GRASS] = Tile(TileType.GRASS, 10, false)
        tiles[TileType.STONE] = Tile(TileType.STONE, 1, true)

        tiles[TileType.BRUSH] = Tile(TileType.BRUSH, 1, false)
    }
}