package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.Tile
import me.rhanjie.compo.game.map.TileType
import me.rhanjie.compo.game.resources.TexturesManager
import me.rhanjie.compo.game.characters.Character
import me.rhanjie.compo.game.ui.Hud

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

        if(terrain.tiles[0][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]!!.type == TileType.STONE) {
            isDead = true
        }

        if (terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()] != null) {
            if (terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]!!.type == TileType.APPLE) {
                bodies.add(Image(TexturesManager.getTexture("snakebody1")))

                if(bodies.size <= 1)
                    bodies.last().setPosition(x, y)

                else bodies.last().setPosition(bodies[bodies.lastIndex - 1].x, bodies[bodies.lastIndex - 1].y)

                stage.addActor(bodies.last())

                speed += 50
                Hud.score += 50

                this.setBodiesColor()

                terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()] = null
            }
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