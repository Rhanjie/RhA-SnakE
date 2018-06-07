package me.rhanjie.compo.game.map

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage

class BonusManager {
    private var bonusesList = hashMapOf<BonusType, Bonus>()
    public var bonuses = mutableListOf<Bonus>()

    init {
        this.load()
    }

    fun addBonus(bonusType: BonusType, positionOnMap: Vector2, stage: Stage){
        bonuses.add(this.getCopy(bonusType, positionOnMap))

        stage.addActor(bonuses.last())
    }

    private fun load(){
        bonusesList[BonusType.SPEED]    = BonusSpeed(BonusType.SPEED)
        bonusesList[BonusType.SLOW]     = BonusSpeed(BonusType.SLOW)

        /*bonusesList[BonusType.INCREASE] = Bonus(BonusType.INCREASE)
        bonusesList[BonusType.DECREASE] = Bonus(BonusType.DECREASE)*/
    }

    private fun getCopy(bonusType: BonusType, positionOnMap: Vector2): Bonus{
        if (bonusesList.containsKey(bonusType) == false)
            return bonusesList[BonusType.SLOW]!!.copy(positionOnMap)

        else return bonusesList[bonusType]!!.copy(positionOnMap)
    }
}