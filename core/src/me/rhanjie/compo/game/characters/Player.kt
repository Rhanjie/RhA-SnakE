package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.Tile
import me.rhanjie.compo.game.map.TileType
import me.rhanjie.compo.game.resources.TexturesManager
import me.rhanjie.compo.game.ui.Hud

class Player constructor(spawnPosition: Vector2, texture: Texture): Image(texture) {
    enum class Direction{
        UP, DOWN, RIGHT, LEFT
    }

    var camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    var bodies: MutableList<Image> = mutableListOf()

    var direction: Direction = Direction.RIGHT
    var smoothPosition: Vector2
    var lastTilePosition: Vector2

    var speed: Float = 400F

    init {
        this.setPosition(spawnPosition.x, spawnPosition.y)
        this.setOrigin(width / 2F, height / 2F)

        lastTilePosition = Vector2(x / Tile.SIZE, y / Tile.SIZE)
        smoothPosition = Vector2(x, y)

        this.update()
    }

    fun handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            direction = Direction.UP
            this.rotation = 90F
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            direction = Direction.DOWN
            this.rotation = 270F
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            direction = Direction.RIGHT
            this.rotation = 0F
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            direction = Direction.LEFT
            this.rotation = 180F
        }

        /*when(direction){
            Direction.UP    -> smoothPosition.y += speed * Gdx.graphics.getDeltaTime()
            Direction.DOWN  -> smoothPosition.y -= speed * Gdx.graphics.getDeltaTime()
            Direction.RIGHT -> smoothPosition.x += speed * Gdx.graphics.getDeltaTime()
            Direction.LEFT  -> smoothPosition.x -= speed * Gdx.graphics.getDeltaTime()
        }*/
    }

    fun move(){
        when(direction){
            Direction.UP    -> smoothPosition.y += speed * Gdx.graphics.getDeltaTime()
            Direction.DOWN  -> smoothPosition.y -= speed * Gdx.graphics.getDeltaTime()
            Direction.RIGHT -> smoothPosition.x += speed * Gdx.graphics.getDeltaTime()
            Direction.LEFT  -> smoothPosition.x -= speed * Gdx.graphics.getDeltaTime()
        }

        if(lastTilePosition.x != (smoothPosition.x / Tile.SIZE).toInt().toFloat() || lastTilePosition.y != (smoothPosition.y / Tile.SIZE).toInt().toFloat()) {
            lastTilePosition.x = (smoothPosition.x / Tile.SIZE).toInt().toFloat()
            lastTilePosition.y = (smoothPosition.y / Tile.SIZE).toInt().toFloat()

            for (index in bodies.size - 1 downTo 0) {
                if (index == 0)
                    bodies[0].setPosition(x, y)
                else bodies[index].setPosition(bodies[index - 1].x, bodies[index - 1].y)
            }
        }
    }

    fun checkCollisions(terrain: Terrain) {
        for (index in (1..bodies.size - 1)) {
            if (x == bodies[index].x && y == bodies[index].y) {
                Gdx.app.exit()
            }
        }

        if(terrain.tiles[0][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]!!.type == TileType.STONE) {
            Gdx.app.exit()
        }

        if (terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()] != null) {
            if (terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()]!!.type == TileType.APPLE) {
                bodies.add(Image(TexturesManager.textures["snakebody1"]))

                if(bodies.size <= 1)
                    bodies.last().setPosition(x, y)

                else bodies.last().setPosition(bodies[bodies.lastIndex - 1].x, bodies[bodies.lastIndex - 1].y)

                stage.addActor(bodies.last())
                speed += 50

                Hud.score += 50

                terrain.tiles[1][(y / Tile.SIZE).toInt()][(x / Tile.SIZE).toInt()] = null
            }
        }
    }

    fun update() {
        this.handleInput()
        this.move()

        x = (smoothPosition.x / Tile.SIZE).toInt() * Tile.SIZE
        y = (smoothPosition.y / Tile.SIZE).toInt() * Tile.SIZE

        camera.position.x = x
        camera.position.y = y

        camera.update()
    }
}