package me.rhanjie.compo.game.map

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage

class BonusManager {
    private var bonusesList = hashMapOf<BonusType, Bonus>()
    public var bonuses = hashMapOf<Vector2, Bonus>()

    init {
        this.load()
    }

    fun addBonus(bonusType: BonusType, positionOnMap: Vector2, stage: Stage){
        bonuses[positionOnMap] = this.getCopy(bonusType, positionOnMap)

        stage.addActor(bonuses[positionOnMap])
    }

    fun update(){
        for(bonus in bonuses)
            bonus.value.update()
    }

    fun getBonusOnPosition(positionOnMap: Vector2): Bonus? = bonuses[positionOnMap]
    fun removeBonus(positionOnMap: Vector2){
        if (bonuses.containsKey(positionOnMap) == true) {
            bonuses[positionOnMap]!!.remove()
            bonuses.remove(positionOnMap)
        }
    }

    private fun load(){
        bonusesList[BonusType.APPLE]    = Apple(BonusType.APPLE)

        bonusesList[BonusType.SPEED]    = BonusSpeed(BonusType.SPEED)
        bonusesList[BonusType.SLOW]     = BonusSpeed(BonusType.SLOW)

        bonusesList[BonusType.INCREASE] = BonusSpeed(BonusType.INCREASE)
        bonusesList[BonusType.DECREASE] = BonusSpeed(BonusType.DECREASE)
    }

    private fun getCopy(bonusType: BonusType, positionOnMap: Vector2): Bonus{
        if (bonusesList.containsKey(bonusType) == false)
            return bonusesList[BonusType.APPLE]!!.copy(positionOnMap)

        else return bonusesList[bonusType]!!.copy(positionOnMap)
    }
}