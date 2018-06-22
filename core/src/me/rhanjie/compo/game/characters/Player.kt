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
import me.rhanjie.compo.game.ui.Hud

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
        this.updateHunger()
        this.updateHUD()
    }

    private fun handleInput() {
        if(this.changedDirection == false) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.W) && direction != Direction.DOWN)
                this.changeDirection(Direction.UP)

            else if (Gdx.input.isKeyJustPressed(Input.Keys.S) && direction != Direction.UP)
                this.changeDirection(Direction.DOWN)

            else if (Gdx.input.isKeyJustPressed(Input.Keys.D) && direction != Direction.LEFT)
                this.changeDirection(Direction.RIGHT)

            else if (Gdx.input.isKeyJustPressed(Input.Keys.A) && direction != Direction.RIGHT)
                this.changeDirection(Direction.LEFT)
        }

        this.move()
    }

    private fun updateCamera() {
        camera.position.x = Math.round(smoothPosition.x).toFloat()
        camera.position.y = Math.round(smoothPosition.y).toFloat()

        //The code below fixing the gaps between the tiles
        if (camera.position.x.toInt() % 2 != 0)
            camera.position.x += 1

        if (camera.position.y.toInt() % 2 != 0)
            camera.position.y += 1

        camera.update()
    }

    private fun updateHunger() {
        hunger -= hungerSpeed * Gdx.graphics.deltaTime

        //Only 99 because label jumping when is 100
        if (hunger > 99F)
            hunger = 99F

        if (hunger <= 0)
            isDead = true
    }

    private fun updateHUD() {
        Hud.score = score
        //TODO: will be added soon
        //Hud.hunger = hunger.toInt()
    }
}