package me.rhanjie.compo.game.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.extend.Align
import me.rhanjie.compo.game.extend.Text
import me.rhanjie.compo.game.resources.TexturesManager


class Hud {
    companion object {
        private var stage: Stage = Stage()
        private lateinit var scoreLabel: Text
        private lateinit var hungryLabel: Text

        var score: Int = 0
        var hunger: Int = 0

        fun create() {
            stage.clear()

            score = 0
            hunger = 0

            this.createLabels()
        }

        fun update(){
            //TODO: Add ui bar for hunger
            scoreLabel.changeText(arrayListOf("Score: $score"))
            hungryLabel.changeText(arrayListOf("Hunger: $hunger/100"))
        }

        fun render() {
            stage.act(Gdx.graphics.deltaTime)
            stage.draw()
        }

        private fun createLabels() {
            val screenFragment = Gdx.graphics.width / 4F

            scoreLabel  = Text(Vector2(screenFragment, 30F), arrayListOf(""), BitmapFont(Gdx.files.internal("font.fnt")), Align.CENTER)
            hungryLabel = Text(Vector2(Gdx.graphics.width - screenFragment, 30F), arrayListOf(""), BitmapFont(Gdx.files.internal("font.fnt")), Align.CENTER)

            scoreLabel.changeColor(255, 255, 0, 150)
            hungryLabel.changeColor(255, 255, 0, 150)

            stage.addActor(scoreLabel)
            stage.addActor(hungryLabel)
        }
    }
}