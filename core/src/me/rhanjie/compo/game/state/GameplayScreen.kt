package me.rhanjie.compo.game.state

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import javafx.scene.input.KeyCode
import me.rhanjie.compo.game.MyGame
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.map.*
import me.rhanjie.compo.game.random
import me.rhanjie.compo.game.resources.TexturesManager
import me.rhanjie.compo.game.ui.Hud

class GameplayScreen constructor(game: MyGame): AbstractManager(game) {
    lateinit var terrain: Terrain
    lateinit var player: Player

    override fun create(){
        super.create()

        terrain = Terrain(2, 30, 20, stage)
        player = Player(Vector2(terrain.tiles[0][0].size * Tile.SIZE / 2, terrain.tiles[0].size * Tile.SIZE / 2), TexturesManager.getTexture("snakehead1"))

        stage = Stage(StretchViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), player.camera), batch)
        stage.addActor(player)

        player.camera.zoom = 2F

        for(layer in terrain.tiles.indices) {
            for (y in terrain.tiles[layer].indices) {
                for (x in terrain.tiles[layer][y].indices) {
                    if((0..100).random() > 95)
                        terrain.bonusManager.addBonus(BonusType.SPEED, Vector2(x.toFloat(), y.toFloat()), stage)
                    else if((0..100).random() > 95){
                        terrain.bonusManager.addBonus(BonusType.APPLE, Vector2(x.toFloat(), y.toFloat()), stage)
                    }
                }
            }
        }

        //stage.addActor(player)
        Hud.create()
    }

    override fun update(delta: Float){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit()

        player.update()
        player.checkCollisions(terrain)
        Hud.update()

        if (player.isDead){
            this.create()
        }

        batch.projectionMatrix = player.camera.combined
    }

    override fun render(delta: Float){
        super.render(delta)
        stage.act(delta)

        batch.begin()
        terrain.render(0, batch)
        batch.end()

        stage.draw()

        batch.begin()
        terrain.render(1, batch)
        batch.end()

        Hud.render()
    }

    override fun dispose(){
        super.dispose()
    }

    fun getMousePositionInGame(): Vector3 = player.camera.unproject(Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0F))
}