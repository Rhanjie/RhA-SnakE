package me.rhanjie.compo.game.map.bonuses

import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.characters.Character
import me.rhanjie.compo.game.characters.Player

class IncreaseBonus constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): AbstractBonus(type, positionOnMap){
    override fun collision(character: Character) {
        character.addBody()

        character.score += 100
    }

    override fun copy(positionOnMap: Vector2): AbstractBonus = IncreaseBonus(type, positionOnMap)
}