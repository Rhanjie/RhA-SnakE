package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import me.rhanjie.compo.resources.TexturesManager
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import me.rhanjie.compo.game.GameManager
import me.rhanjie.compo.game.map.Tile

class Player constructor(spawnPoint: Float, texture: Texture): Person(texture) {
    var camera = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    var collisionBox: Rectangle = Rectangle(camera.position.x, 0F, 32F, 64F)

    var orangeJuice: Int = 100



    init {
        camera.position.set(spawnPoint, camera.viewportHeight / 3F, 0F)
        camera.update()

        this.setPosition(camera.viewportWidth / 2F, camera.viewportHeight / 4F - 28F)

        collisionBox.y = GameManager.spawnY
    }

    fun handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.position.x -= 5F

            this.setFlip(true, false)
        }

        else if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            camera.position.x += 5F

            this.setFlip(false, false)
        }

        camera.update()

        collisionBox.x = camera.position.x
    }

    fun checkCollisions(npcManager: NpcManager){
        for(npc in npcManager.npcList) {
            var x = npc.boundingRectangle.x
            var w = npc.boundingRectangle.width

            //if(collisionBox.x < x + w && collisionBox.x + collisionBox.width > x){
            if(collisionBox.overlaps(npc.boundingRectangle)){
                npc.active = true

                if (npc.currentState == NpcState.UNEMPLOYED && Gdx.input.isKeyPressed(Input.Keys.S)) {
                    npc.currentState   = NpcState.WAITING
                    npc.currentTask    = NpcTasks.WALK
                    npc.targetPosition = GameManager.spawnX

                    orangeJuice--
                }

                break
            }
        }
    }
}