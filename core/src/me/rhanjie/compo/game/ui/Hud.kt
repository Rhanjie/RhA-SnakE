package me.rhanjie.compo.game.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.extend.Text


class Hud {
    companion object {
        var stage: Stage = Stage()
        var scoreLabel: Text = Text()

        var score: Int = 0

        fun create() {
            score = 0

            scoreLabel.text = "Score: "
            scoreLabel.x = Gdx.graphics.width / 2F
            scoreLabel.y = 100F

            stage.addActor(scoreLabel)
        }

        fun update(){
            scoreLabel.text = "Score: $score"
        }

        fun render() {
            stage.act(Gdx.graphics.deltaTime)
            stage.draw()
        }
    }
}