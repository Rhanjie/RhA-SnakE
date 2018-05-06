package me.rhanjie.compo.game.state

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import me.rhanjie.compo.game.MyGame
import me.rhanjie.compo.game.resources.TexturesManager

abstract class AbstractManager constructor(game: MyGame): Screen {
    lateinit var batch: SpriteBatch
    lateinit var stage: Stage

    init {
        this.create()
    }

    open fun create() {
        batch = SpriteBatch()
        stage = Stage(StretchViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat()), batch)

        Gdx.input.inputProcessor = stage
    }

    abstract fun update()

    private fun clearScreen(r: Float, g: Float, b: Float) {
        Gdx.gl.glClearColor(r/255, g/255, b/255, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

    override fun render(delta: Float) {
        this.update()

        this.clearScreen(0F, 125F, 255F)
    }

    override fun dispose() {
        TexturesManager.dispose()

        stage.dispose()
        batch.dispose()
    }

    override fun hide() {
    }

    override fun show() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }
}