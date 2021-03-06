package me.rhanjie.compo.game.map.bonuses

import com.badlogic.gdx.math.Vector2
import me.rhanjie.compo.game.characters.Character
import me.rhanjie.compo.game.characters.Player

class SlowBonus constructor(type: BonusType, positionOnMap: Vector2 = Vector2(0F, 0F)): AbstractBonus(type, positionOnMap){
    override fun collision(character: Character) {
        if (character.speed > 200)
            character.speed -= 100

        character.score += 200
    }

    override fun copy(positionOnMap: Vector2): AbstractBonus = SlowBonus(type, positionOnMap)
}