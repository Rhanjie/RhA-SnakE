package me.rhanjie.compo.game.characters

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.bonuses.AbstractBonus
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.map.tiles.TileType
import me.rhanjie.compo.game.resources.TexturesManager

abstract class Character constructor(texture: TextureRegion, val id: Int = 1): Image(texture) {
    enum class Direction{
        UP, DOWN, RIGHT, LEFT
    }

    var bodies: MutableList<Image> = mutableListOf()
    var direction: Direction = Direction.RIGHT

    lateinit var smoothPosition: Vector2
    lateinit var lastTilePosition: Vector2

    var speed: Float = 400F
    var isDead: Boolean = false

    open fun update(terrain: Terrain){
        this.checkCollisions(terrain)
    }

    public fun addBody(){
        bodies.add(Image(TexturesManager.getTexture("snakebody$id")))

        if (bodies.size <= 1)
            bodies.last().setPosition(x, y)

        else bodies.last().setPosition(bodies[bodies.lastIndex - 1].x, bodies[bodies.lastIndex - 1].y)

        stage.addActor(bodies.last())
        this.setBodiesColor()
    }

    public fun removeBody(){
        if (bodies.size >= 1) {
            stage.unfocus(bodies.last())

            bodies.last().remove()
            bodies.remove(bodies.last())

            this.setBodiesColor()
        }
    }

    public open fun checkCollisions(terrain: Terrain) {
        for (index in (1..bodies.size - 1)) {
            if (x == bodies[index].x && y == bodies[index].y) {
                isDead = true
            }
        }

        var tile: Tile? = terrain.tiles[0][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]
        if (tile != null) {
            if (tile.type == TileType.STONE) {
                isDead = true
            }
        }

        tile = terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]
        if (tile != null) {
            //...
        }

        var bonus: AbstractBonus? = terrain.bonusManager.getBonusOnPosition(Vector2((x / Tile.SIZE), (y / Tile.SIZE)))
        if (bonus != null){
            bonus.collision(this)

            terrain.bonusManager.removeBonus(Vector2((x / Tile.SIZE), (y / Tile.SIZE)))
        }
    }

    protected fun move(){
        when(direction){
            Direction.UP    -> smoothPosition.y += speed * Gdx.graphics.deltaTime
            Direction.DOWN  -> smoothPosition.y -= speed * Gdx.graphics.deltaTime
            Direction.RIGHT -> smoothPosition.x += speed * Gdx.graphics.deltaTime
            Direction.LEFT  -> smoothPosition.x -= speed * Gdx.graphics.deltaTime
        }

        if (lastTilePosition.x != (smoothPosition.x / Tile.SIZE).toInt().toFloat() || lastTilePosition.y != (smoothPosition.y / Tile.SIZE).toInt().toFloat()) {
            lastTilePosition.x = (smoothPosition.x / Tile.SIZE).toInt().toFloat()
            lastTilePosition.y = (smoothPosition.y / Tile.SIZE).toInt().toFloat()

            for (index in bodies.size - 1 downTo 0) {
                if (index == 0)
                    bodies[0].setPosition(x, y)

                else bodies[index].setPosition(bodies[index - 1].x, bodies[index - 1].y)
            }
        }
    }

    protected fun setBodiesColor(){
        for (index in bodies.size - 1 downTo 0) {
            val forceChangingColor = 5F

            bodies[index].color = Color(1F - forceChangingColor * index / 255F, 1F - forceChangingColor * index / 255F, 1F - forceChangingColor * index / 255F, 1F)
        }
    }
}