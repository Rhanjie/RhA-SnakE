package me.rhanjie.compo.game.map.bonuses

import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.characters.Character
import me.rhanjie.compo.game.characters.Player

class DecreaseBonus constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): AbstractBonus(type, positionOnMap){
    override fun collision(character: Character) {
        character.removeBody()
        character.removeBody()
    }

    override fun copy(positionOnMap: Vector2): AbstractBonus = DecreaseBonus(type, positionOnMap)
}