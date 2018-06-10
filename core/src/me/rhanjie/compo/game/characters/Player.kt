package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.map.tiles.TileType
import me.rhanjie.compo.game.map.bonuses.AbstractBonus

class Player constructor(spawnPosition: Vector2, texture: Texture): Character(texture) {
    var camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    init {
        this.setPosition(spawnPosition.x, spawnPosition.y)
        this.setOrigin(width / 2F, height / 2F)

        lastTilePosition = Vector2(x / Tile.SIZE, y / Tile.SIZE)
        smoothPosition = Vector2(x, y)
    }

    fun checkCollisions(terrain: Terrain) {
        for (index in (1..bodies.size - 1)) {
            if (x == bodies[index].x && y == bodies[index].y) {
                isDead = true
            }
        }

        var tile: Tile? = terrain.tiles[0][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]
        if (tile != null) {
            if (terrain.tiles[0][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]!!.type == TileType.STONE) {
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

    override fun update() {
        this.handleInput()

        x = (smoothPosition.x / Tile.SIZE).toInt() * Tile.SIZE
        y = (smoothPosition.y / Tile.SIZE).toInt() * Tile.SIZE

        camera.position.x = smoothPosition.x
        camera.position.y = smoothPosition.y

        camera.update()
    }

    private fun handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && direction != Direction.DOWN) {
            direction = Direction.UP
            this.rotation = 90F
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) && direction != Direction.UP) {
            direction = Direction.DOWN
            this.rotation = 270F
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) && direction != Direction.LEFT) {
            direction = Direction.RIGHT
            this.rotation = 0F
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) && direction != Direction.RIGHT) {
            direction = Direction.LEFT
            this.rotation = 180F
        }

        this.move()
    }
}