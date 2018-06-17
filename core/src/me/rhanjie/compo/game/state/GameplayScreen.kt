package me.rhanjie.compo.game.state

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.StretchViewport
import me.rhanjie.compo.game.MyGame
import me.rhanjie.compo.game.characters.EnemyManager
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.map.*
import me.rhanjie.compo.game.map.bonuses.BonusType
import me.rhanjie.compo.game.map.tiles.Tile
import me.rhanjie.compo.game.random
import me.rhanjie.compo.game.resources.TexturesManager
import me.rhanjie.compo.game.ui.Hud

class GameplayScreen constructor(game: MyGame): AbstractManager(game) {
    lateinit var terrain: Terrain
    lateinit var player: Player
    lateinit var enemyManager: EnemyManager

    companion object {
        val terrainLayers = 2
        val terrainWidth = 20
        val terrainHeight = 20
    }

    override fun create(){
        super.create()

        player = Player(Vector2(terrainWidth * Tile.SIZE / 2, terrainHeight * Tile.SIZE / 2), TexturesManager.getTexture("snakehead1"))
        player.camera.zoom = 2F

        stage = Stage(StretchViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat(), player.camera), batch)
        stage.addActor(player)

        terrain = Terrain(terrainLayers, terrainWidth, terrainHeight, stage)

        enemyManager = EnemyManager(stage)
        enemyManager.addEnemy(Vector2(terrainWidth * Tile.SIZE / 2, terrainHeight * Tile.SIZE / 2))

        Hud.create()
    }

    override fun update(delta: Float){
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
            Gdx.app.exit()

        player.update(terrain)
        terrain.update()
        enemyManager.update(terrain)

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