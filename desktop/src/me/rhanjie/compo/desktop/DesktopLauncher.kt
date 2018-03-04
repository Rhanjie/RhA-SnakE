package me.rhanjie.compo.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import me.rhanjie.compo.game.GameManager

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
            config.width  = 1920
            config.height = 1080

        LwjglApplication(GameManager(), config)
    }
}
