package me.rhanjie.compo.game

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import me.rhanjie.compo.game.state.GameplayScreen
import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

class MyGame: Game() {
    companion object {
        val WIDTH = 1366
        val HEIGHT = 768
        val TITLE = "SnakeGame v.1.1"
    }

    override fun create() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        this.setScreen(GameplayScreen(this))
    }
}