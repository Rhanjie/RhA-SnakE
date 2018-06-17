package me.rhanjie.compo.game.characters

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.resources.TexturesManager

class EnemyManager constructor(val stage: Stage) {
    var enemies: ArrayList<EnemySnake> = arrayListOf()

    fun addEnemy(position: Vector2){
        enemies.add(EnemySnake(position, TexturesManager.getTexture("snakehead2")))

        this.stage.addActor(enemies.last())
    }

    fun update(terrain: Terrain){
        for(enemy in enemies) {
            enemy.update(terrain)

            if (enemy.isDead) {
                for (body in enemy.bodies) {
                    this.stage.unfocus(body)

                    body.remove()
                }

                enemies.remove(enemy)
                enemy.remove()

                return
            }
        }
    }
}