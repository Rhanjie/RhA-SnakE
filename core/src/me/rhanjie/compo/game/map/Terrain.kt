package me.rhanjie.compo.game.map

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import me.rhanjie.compo.game.random
import me.rhanjie.compo.resources.TexturesManager

class Terrain constructor(width: Int){
    var tiles = Array(width, {Tile(TileType.GRASS, (0..3).random())})

    fun render(batch: SpriteBatch){
        var i = 0

        for(tile in tiles){
            if(tile.type == TileType.GRASS) {
                batch.draw(TextureRegion(TexturesManager.textures["grass"], Tile.SIZE * tile.which, 0, Tile.SIZE, Tile.SIZE), Tile.SIZE.toFloat() * i++, 0f)
            }

            //...
        }
    }

    fun renderOther(batch: SpriteBatch){
        var i = 0
        var sprite: Sprite

        for(tile in tiles){
            if(tile.type == TileType.GRASS) {
                //TODO: Test code
                sprite = Sprite(TextureRegion(TexturesManager.textures["grass"], Tile.SIZE * tile.which, 0, Tile.SIZE, Tile.SIZE))
                sprite.x = Tile.SIZE.toFloat() * i++
                sprite.y = -64F
                //sprite.rotate(180F)
                sprite.setFlip(false, true)

                sprite.draw(batch)
            }
        }
    }
}