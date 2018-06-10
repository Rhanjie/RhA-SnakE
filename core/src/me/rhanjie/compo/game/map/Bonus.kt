package me.rhanjie.compo.game.map

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.resources.TexturesManager
import me.rhanjie.compo.game.ui.Hud

enum class BonusType {
    APPLE, SPEED, SLOW, INCREASE, DECREASE
}

abstract class Bonus constructor(var type: BonusType, var positionOnMap: Vector2)
    : Image(TexturesManager.getTexture(type.name.toLowerCase())) {

    init {
        this.x = positionOnMap.x * Tile.SIZE
        this.y = positionOnMap.y * Tile.SIZE

        this.setOrigin((Tile.SIZE / 2).toInt())
    }

    abstract fun copy(positionOnMap: Vector2): Bonus
    abstract fun collision(player: Player)

    open fun update(){
        this.rotateBy(1F)
    }
}

class Apple constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): Bonus(type, positionOnMap){
    override fun collision(player: Player) {
        player.addBody()

        Hud.score += 50
    }

    override fun copy(positionOnMap: Vector2): Apple = Apple(type, positionOnMap)
}

class BonusSpeed constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): Bonus(type, positionOnMap){
    override fun collision(player: Player) {
        player.speed += 100
    }

    override fun copy(positionOnMap: Vector2): Bonus = BonusSpeed(type, positionOnMap)
}