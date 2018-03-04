package me.rhanjie.compo.game.characters

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import me.rhanjie.compo.game.random
import me.rhanjie.compo.resources.TexturesManager

enum class NpcTasks{
    WAIT, WALK
}

enum class NpcState{
    UNEMPLOYED, WAITING, COLLECTING, BRINGING
}

class Npc constructor(name: String, x: Float, y: Float, texture: Texture): Person(texture) {
    var currentTask: NpcTasks = NpcTasks.WAIT
    var currentState: NpcState = NpcState.UNEMPLOYED
    var targetPosition = 0F

    var font: BitmapFont = BitmapFont()
    var active: Boolean = false

    init {
        this.setPosition(x, y)

        if((0..100).random() >= 50)
            this.setFlip(true, false)

        this.name = name
    }

    fun update(){
        if(currentTask == NpcTasks.WALK){
            if(this.x > targetPosition) {
                this.x -= 3F

                this.setFlip(true, false)
            }

            else if(this.x < targetPosition) {
                this.x += 3F

                this.setFlip(false, false)
            }

            else currentTask = NpcTasks.WAIT
        }

        active = false
    }

    fun render(batch: SpriteBatch){
        if(active) {
            font.draw(batch, name, this.x, this.y + 80F)

            if(currentState == NpcState.UNEMPLOYED)
                font.draw(batch, "Click S to hire the unemployed", this.x, 500F)
        }
    }
}