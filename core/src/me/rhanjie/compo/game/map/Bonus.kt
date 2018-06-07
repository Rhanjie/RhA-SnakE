package me.rhanjie.compo.game.map

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.resources.TexturesManager
import me.rhanjie.compo.game.ui.Hud

enum class BonusType {
    SPEED, SLOW, INCREASE, DECREASE
}

abstract class Bonus constructor(var type: BonusType, positionOnMap: Vector2)
    : Image(TexturesManager.getTexture(type.name.toLowerCase())) {

    init {
        this.x = positionOnMap.x * Tile.SIZE
        this.y = positionOnMap.y * Tile.SIZE
    }

    abstract fun copy(positionOnMap: Vector2): Bonus
    abstract fun collision()
}

class BonusSpeed constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): Bonus(type, positionOnMap){
    override fun collision() {
        Hud.score += 1234
    }

    override fun copy(positionOnMap: Vector2): Bonus = BonusSpeed(type, positionOnMap)
}