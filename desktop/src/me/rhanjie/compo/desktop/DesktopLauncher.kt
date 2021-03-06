package me.rhanjie.compo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import me.rhanjie.compo.game.MyGame
import me.rhanjie.compo.game.state.GameplayScreen

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
            config.width  = MyGame.WIDTH
            config.height = MyGame.HEIGHT
            config.title  = MyGame.TITLE

        LwjglApplication(MyGame(), config)
    }
}
