package me.rhanjie.compo.game.map.bonuses

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage

class BonusManager {
    private var bonusesList = hashMapOf<BonusType, AbstractBonus>()
    public var bonuses = hashMapOf<Vector2, AbstractBonus>()

    init {
        this.load()
    }

    fun addBonus(BonusType: BonusType, positionOnMap: Vector2, stage: Stage){
        bonuses[positionOnMap] = this.getCopy(BonusType, positionOnMap)

        stage.addActor(bonuses[positionOnMap])
    }

    fun update(){
        for(AbstractBonus in bonuses)
            AbstractBonus.value.update()
    }

    fun getBonusOnPosition(positionOnMap: Vector2): AbstractBonus? = bonuses[positionOnMap]
    fun removeBonus(positionOnMap: Vector2){
        if (bonuses.containsKey(positionOnMap) == true) {
            bonuses[positionOnMap]!!.remove()
            bonuses.remove(positionOnMap)
        }
    }

    private fun load(){
        bonusesList[BonusType.APPLE]    = AppleBonus(BonusType.APPLE)

        //TODO: Add other bonuses
        bonusesList[BonusType.SPEED]    = SpeedBonus(BonusType.SPEED)
        bonusesList[BonusType.SLOW]     = SpeedBonus(BonusType.SLOW)

        bonusesList[BonusType.INCREASE] = SpeedBonus(BonusType.INCREASE)
        bonusesList[BonusType.DECREASE] = SpeedBonus(BonusType.DECREASE)
    }

    private fun getCopy(bonusType: BonusType, positionOnMap: Vector2): AbstractBonus {
        if (bonusesList.containsKey(bonusType) == false)
            return bonusesList[BonusType.APPLE]!!.copy(positionOnMap)

        else return bonusesList[bonusType]!!.copy(positionOnMap)
    }
}