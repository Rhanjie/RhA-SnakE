package me.rhanjie.compo.game.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.extend.Align
import me.rhanjie.compo.game.extend.Text
import me.rhanjie.compo.game.resources.TexturesManager


class Hud {
    companion object {
        var stage: Stage = Stage()
        var scoreLabel: Text = Text(Vector2(0F, 0F), arrayListOf("Score: 0"), BitmapFont(Gdx.files.internal("font.fnt")), Align.CENTER)

        var score: Int = 0
        var hunger: Int = 0

        fun create() {
            score = 0
            hunger = 0

            scoreLabel.x = Gdx.graphics.width / 2F
            scoreLabel.y = 70F
            scoreLabel.changeColor(255, 255, 0, 150)

            stage.addActor(scoreLabel)
        }

        fun update(){
            //TODO: Add ui bar for hunger
            scoreLabel.changeText(arrayListOf("Score: $score", "Hunger: $hunger/100"))
        }

        fun render() {
            stage.act(Gdx.graphics.deltaTime)
            stage.draw()
        }
    }
}