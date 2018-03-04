package me.rhanjie.compo.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import me.rhanjie.compo.resources.TexturesManager
import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

abstract class AbstractManager: Game() {
    lateinit var staticBatch: SpriteBatch
    lateinit var batch: SpriteBatch

    override fun create(){
        batch = SpriteBatch()
        staticBatch = SpriteBatch()

        TexturesManager.load()
    }

    abstract fun update()
    override fun render(){
        this.update()
    }

    override fun dispose(){
        batch.dispose()
        staticBatch.dispose()

        TexturesManager.dispose()
    }

    fun clearScreen(r: Float, g: Float, b: Float){
        Gdx.gl.glClearColor(r, g, b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }
}