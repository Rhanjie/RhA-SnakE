package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.bonuses.AbstractBonus
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.map.tiles.TileType

class EnemySnake constructor(spawnPosition: Vector2, texture: TextureRegion): Character(texture) {
    var tName: String = "unnamed"
    var target: Vector2? = null

    init {
        this.setPosition(spawnPosition.x, spawnPosition.y)
        this.setOrigin(width / 2F, height / 2F)

        lastTilePosition = Vector2(x / Tile.SIZE, y / Tile.SIZE)
        smoothPosition = Vector2(x, y)
    }

    override fun update(terrain: Terrain) {
        super.update(terrain)
        this.move()

        x = (smoothPosition.x / Tile.SIZE).toInt() * Tile.SIZE
        y = (smoothPosition.y / Tile.SIZE).toInt() * Tile.SIZE

        testAI(terrain)
    }

    //TODO: Implement A*
    private fun findPathTo(){
        //...

    }

    //TODO: Temporary function. Delete it when A* will be implemented
    private fun testAI(terrain: Terrain){
        if(target != null){
            var bonus: AbstractBonus? = terrain.bonusManager.getBonusOnPosition(target!!)
            if (bonus == null){
                target = null
            }
        }

        if(target == null){
            endloop@
            for(Y in (y / Tile.SIZE - 5).toInt()..(y / Tile.SIZE + 5).toInt()){
                for(X in (x / Tile.SIZE - 5).toInt()..(x / Tile.SIZE + 5).toInt()){
                    var bonus: AbstractBonus? = terrain.bonusManager.getBonusOnPosition(Vector2(X.toFloat(), Y.toFloat()))

                    if (bonus != null){
                        target = Vector2(X.toFloat(), Y.toFloat())

                        break@endloop;
                    }
                }
            }
        }

        if(target != null){
            if (lastTilePosition.x > target!!.x)
                direction = Direction.LEFT
            else if (lastTilePosition.x < target!!.x)
                direction = Direction.RIGHT

            if (lastTilePosition.y > target!!.y)
                direction = Direction.DOWN
            else if (lastTilePosition.y < target!!.y)
                direction = Direction.UP
        }

        var tile: Tile? = null

        if(direction == Direction.RIGHT)
            tile = terrain.tiles[0][lastTilePosition.y.toInt()][(lastTilePosition.x + 1).toInt()]
        if(direction == Direction.LEFT)
            tile = terrain.tiles[0][lastTilePosition.y.toInt()][(lastTilePosition.x - 1).toInt()]
        if(direction == Direction.UP)
            tile = terrain.tiles[0][(lastTilePosition.y + 1).toInt()][lastTilePosition.x.toInt()]
        if(direction == Direction.DOWN)
            tile = terrain.tiles[0][(lastTilePosition.y - 1).toInt()][lastTilePosition.x.toInt()]

        if (tile != null) {
            if (tile.type == TileType.STONE) {
                when(direction){
                    Direction.DOWN  -> direction = Direction.LEFT
                    Direction.LEFT  -> direction = Direction.UP
                    Direction.UP    -> direction = Direction.RIGHT
                    Direction.RIGHT -> direction = Direction.DOWN
                }
            }
        }
    }
}