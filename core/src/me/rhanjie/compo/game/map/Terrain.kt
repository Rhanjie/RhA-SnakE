package me.rhanjie.compo.game.map

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import me.rhanjie.compo.game.resources.TexturesManager
import com.badlogic.gdx.scenes.scene2d.Stage
import me.rhanjie.compo.game.random


class Terrain constructor(layers: Int, width: Int, height: Int,  stage: Stage){
    var tiles: Array<Array<Array<Tile?>>> = Array(layers, {Array(height, {Array<Tile?>(width, {Tile(TileType.GRASS, TexturesManager.textures["grass${(1..11).random()}"]!!)})})})

    init {
        for(layer in tiles.indices) {
            for (y in tiles[layer].indices) {
                for (x in tiles[layer][y].indices) {
                    if(layer == 0){
                        if(x == 0 || x == tiles[layer][y].size - 1 || y == 0 || y == tiles[layer].size - 1){
                            tiles[layer][y][x] = Tile(TileType.STONE, TexturesManager.textures["stone1"]!!)
                        }
                    }

                    if(layer == 1) {
                        tiles[layer][y][x] = null

                        if ((0..100).random() > 80 && tiles[0][y][x]!!.type != TileType.STONE) {
                            tiles[layer][y][x] = Tile(TileType.BRUSH, TexturesManager.textures["brush1"]!!)
                        }

                        if ((0..100).random() > 90 && tiles[0][y][x]!!.type != TileType.STONE) {
                            tiles[layer][y][x] = Tile(TileType.APPLE, TexturesManager.textures["apple1"]!!)
                        }
                    }

                    if (tiles[layer][y][x] != null) {
                        tiles[layer][y][x]!!.x = x * Tile.SIZE
                        tiles[layer][y][x]!!.y = y * Tile.SIZE

                        //stage.addActor(tiles[layer][y][x])
                    }
                }
            }
        }
    }

    fun setTile(layer: Int, x: Int, y: Int, tile: Tile){
        tiles[layer][y][x] = tile

        tiles[layer][y][x]!!.x = x * Tile.SIZE
        tiles[layer][y][x]!!.y = y * Tile.SIZE
    }

    fun update(){
        //...
    }

    fun render(layer: Int, batch: SpriteBatch){
        for (y in tiles[layer].indices) {
            for (x in tiles[layer][y].indices) {
                if (tiles[layer][y][x] != null)
                    tiles[layer][y][x]!!.draw(batch)
            }
        }
    }

    fun getTile(layers: Int, x: Float, y: Float): Tile?{
        if(tiles[layers][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()] == null)
            return null

        return tiles[layers][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]
    }
}