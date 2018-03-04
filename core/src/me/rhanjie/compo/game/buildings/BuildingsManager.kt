package me.rhanjie.compo.game.buildings

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import me.rhanjie.compo.game.GameManager
import me.rhanjie.compo.resources.TexturesManager

class BuildingsManager {
    var mainHouse: House = House(TexturesManager.textures["house"]!!)



    init{
        mainHouse.x = GameManager.spawnX - mainHouse.originX
        mainHouse.y = GameManager.spawnY
    }

    fun render(batch: SpriteBatch){
        mainHouse.draw(batch)
    }
}