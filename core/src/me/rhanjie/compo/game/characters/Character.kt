package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image

abstract class Character constructor(texture: Texture): Image(texture) {
    enum class Direction{
        UP, DOWN, RIGHT, LEFT
    }

    var direction: Direction = Direction.RIGHT

    lateinit var smoothPosition: Vector2
    lateinit var lastTilePosition: Vector2

    var speed: Float = 400F
    var isDead: Boolean = false

    abstract fun update()
}