package me.rhanjie.compo.game.extend

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.Actor

class Text: Actor() {
    var font: BitmapFont = BitmapFont()
    var text: String = ""

    fun update(text: String){
        this.text = text
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        font.draw(batch, text, x, y)
    }
}