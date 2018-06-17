package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.map.tiles.TileType
import me.rhanjie.compo.game.map.bonuses.AbstractBonus

class Player constructor(spawnPosition: Vector2, texture: TextureRegion): Character(texture) {
    var camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    init {
        this.setPosition(spawnPosition.x, spawnPosition.y)
        this.setOrigin(width / 2F, height / 2F)

        lastTilePosition = Vector2(x / Tile.SIZE, y / Tile.SIZE)
        smoothPosition = Vector2(x, y)
    }

    override fun update(terrain: Terrain) {
        super.update(terrain)
        this.handleInput()

        this.updateCamera()
    }

    private fun updateCamera() {
        camera.position.x = smoothPosition.x.toInt().toFloat()
        camera.position.y = smoothPosition.y.toInt().toFloat()

        //The code below fixing the gaps between the tiles
        if (camera.position.x.toInt() % 2 != 0)
            camera.position.x += 1

        if (camera.position.y.toInt() % 2 != 0)
            camera.position.y += 1

        camera.update()
    }

    private fun handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W) && direction != Direction.DOWN)
            this.changeDirection(Direction.UP)

        if (Gdx.input.isKeyPressed(Input.Keys.S) && direction != Direction.UP)
            this.changeDirection(Direction.DOWN)

        if (Gdx.input.isKeyPressed(Input.Keys.D) && direction != Direction.LEFT)
            this.changeDirection(Direction.RIGHT)

        if (Gdx.input.isKeyPressed(Input.Keys.A) && direction != Direction.RIGHT)
            this.changeDirection(Direction.LEFT)

        this.move()
    }
}