package me.rhanjie.compo.game.map

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import me.rhanjie.compo.game.map.bonuses.BonusManager
import me.rhanjie.compo.game.map.bonuses.BonusType
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.map.tiles.TileManager
import me.rhanjie.compo.game.map.tiles.TileType
import me.rhanjie.compo.game.random


class Terrain constructor(layers: Int, width: Int, height: Int, stage: Stage){
    var tileManager: TileManager = TileManager()
    var tiles: Array<Array<Array<Tile?>>> = Array(layers, {Array(height, {Array<Tile?>(width, {tileManager.getCopy(TileType.GRASS)} )} )} )

    var bonusManager: BonusManager = BonusManager()

    init {
        for(layer in tiles.indices) {
            for(y in tiles[layer].indices) {
                for(x in tiles[layer][y].indices) {
                    if (layer == 0){
                        if(x == 0 || x == tiles[layer][y].size - 1 || y == 0 || y == tiles[layer].size - 1){
                            tiles[layer][y][x] = tileManager.getCopy(TileType.STONE)
                        }

                        //...
                    }

                    if (layer == 1) {
                        tiles[layer][y][x] = null

                        if ((0..100).random() > 80 && tiles[0][y][x]!!.type != TileType.STONE) {
                            tiles[layer][y][x] = tileManager.getCopy(TileType.BRUSH)
                        }

                        //TODO: Add dynamic bonus spawning
                        if ((0..100).random() > 96 && tiles[0][y][x]!!.type != TileType.STONE) {
                            bonusManager.addBonus(BonusType.APPLE, Vector2(x.toFloat(), y.toFloat()), stage)
                        }

                        else if ((0..100).random() > 98 && tiles[0][y][x]!!.type != TileType.STONE) {
                            bonusManager.addBonus(BonusType.SPEED, Vector2(x.toFloat(), y.toFloat()), stage)
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
        bonusManager.update()
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