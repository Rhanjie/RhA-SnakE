package me.rhanjie.compo.game

import com.badlogic.gdx.Game
import me.rhanjie.compo.game.state.GameplayScreen
import java.util.*

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

class MyGame: Game() {
    override fun create() {
        this.setScreen(GameplayScreen(this))
    }
}