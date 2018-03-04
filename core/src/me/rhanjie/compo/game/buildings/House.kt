package me.rhanjie.compo.game.buildings

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite

class House constructor(texture: Texture): Sprite(texture){
    var tenants: Int = 0

    init {
        this.setOrigin(this.width / 2F, 0F)
    }
}