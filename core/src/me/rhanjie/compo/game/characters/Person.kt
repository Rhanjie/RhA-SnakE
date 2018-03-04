package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle

abstract class Person constructor(texture: Texture): Sprite(texture) {
    var name: String = "Unnamed"
}