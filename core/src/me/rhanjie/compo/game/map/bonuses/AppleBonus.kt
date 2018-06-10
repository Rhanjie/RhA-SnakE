package me.rhanjie.compo.game.map.bonuses

import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.ui.Hud

class AppleBonus constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): AbstractBonus(type, positionOnMap){
    override fun collision(player: Player) {
        player.addBody()

        Hud.score += 50
    }

    override fun copy(positionOnMap: Vector2): AppleBonus = AppleBonus(type, positionOnMap)
}