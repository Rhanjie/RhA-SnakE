package me.rhanjie.compo.game.ui

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.Stage
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.extend.Text


class Hud {
    companion object {
        var stage: Stage = Stage()

        //var style = TextButtonStyle()
        //lateinit var button: Button

        var orangeJuiceLabel = Text()
        var tenantsLabel     = Text()

        fun create() {
            //style.up   = TextureRegionDrawable(TextureRegion(TexturesManager.textures["hud"]))
            //style.down = TextureRegionDrawable(TextureRegion(TexturesManager.textures["shop"]))

            //button = Button(style)
            //button.width = 300F

            //stage.addActor(button)
            stage.addActor(orangeJuiceLabel)
            stage.addActor(tenantsLabel)

            orangeJuiceLabel.x = Gdx.graphics.width/2F
            orangeJuiceLabel.y = 100F

            tenantsLabel.x = Gdx.graphics.width/2F
            tenantsLabel.y = 80F
        }

        fun update(player: Player){
            orangeJuiceLabel.update("You have ${player.orangeJuice} orange juices")
        }

        fun render() {
            stage.act(Gdx.graphics.deltaTime)

            stage.draw()
        }
    }
}