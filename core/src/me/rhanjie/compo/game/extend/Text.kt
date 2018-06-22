package me.rhanjie.compo.game.extend

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor

enum class Align {
    LEFT, CENTER, RIGHT
}

class Text constructor(position: Vector2, private var textLines: ArrayList<String> = arrayListOf(""),
                       private val font: BitmapFont = BitmapFont(), private var align: Align = Align.LEFT): Actor() {

    private var offset: Float = 0F

    init {
        x = position.x
        y = position.y

        this.updateOffset()
    }

    fun changeText(textLines: ArrayList<String>){
        this.textLines = textLines

        var longestLine = 0
        for(text in textLines){
            if (longestLine < text.length)
                longestLine = text.length
        }

        height = font.lineHeight
        width = longestLine * height

        this.updateOffset()
    }

    //TODO: Setting color doesn't work
    fun changeColor(r: Int, g: Int, b: Int, a: Int){
        font.setColor(r / 255F, g / 255F, b / 255F, a / 255F)
    }

    fun changeScale(scale: Float){
        font.data.setScale(scale)
    }

    fun setAlign(align: Align){
        this.align = align

        this.updateOffset()
    }

    private fun updateOffset() {
        offset = when(align){
            Align.LEFT   -> 0F
            Align.CENTER -> width / 2
            Align.RIGHT  -> width
        }

        println(offset)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        for(i in textLines.indices)
            font.draw(batch, textLines[i], x - offset, y - (height * i))
    }
}