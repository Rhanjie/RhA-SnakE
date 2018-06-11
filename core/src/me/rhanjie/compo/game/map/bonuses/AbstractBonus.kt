package me.rhanjie.compo.game.map.bonuses

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.resources.TexturesManager

enum class BonusType {
    APPLE, SPEED, SLOW, INCREASE, DECREASE
}

abstract class AbstractBonus constructor(var type: BonusType, var positionOnMap: Vector2)
    : Image(TexturesManager.getTexture(type.name.toLowerCase()+"1")) {

    init {
        this.x = positionOnMap.x * Tile.SIZE
        this.y = positionOnMap.y * Tile.SIZE

        this.setOrigin((Tile.SIZE / 2).toInt())
    }

    abstract fun copy(positionOnMap: Vector2): AbstractBonus
    abstract fun collision(player: Player)

    open fun update(){
        //TODO: Just debug
        this.rotateBy(3F)
    }
}