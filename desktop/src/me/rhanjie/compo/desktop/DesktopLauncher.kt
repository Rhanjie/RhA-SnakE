package me.rhanjie.compo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import me.rhanjie.compo.game.MyGame
import me.rhanjie.compo.game.state.GameplayScreen

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
            config.width  = 1366
            config.height = 768
            //config.fullscreen = true

        LwjglApplication(MyGame(), config)
    }
}
