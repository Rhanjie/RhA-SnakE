package me.rhanjie.compo.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import me.rhanjie.compo.game.buildings.BuildingsManager
import me.rhanjie.compo.game.buildings.House
import me.rhanjie.compo.game.characters.NpcManager
import me.rhanjie.compo.game.characters.NpcState
import me.rhanjie.compo.game.characters.Player
import me.rhanjie.compo.game.map.Terrain
import me.rhanjie.compo.game.map.Tile
import me.rhanjie.compo.game.ui.Hud
import me.rhanjie.compo.resources.TexturesManager

class GameManager: AbstractManager() {
    companion object {
        var spawnX = 0F
        var spawnY = 0F
    }

    lateinit var terrain: Terrain
    lateinit var player: Player

    lateinit var npcManager: NpcManager
    lateinit var buildingsManager: BuildingsManager

    //TODO: Load from json file
    var names = arrayOf("RhAnjiE", "Adikso", "Gbh", "Arc", "Skerv", "Revolver", "Error", "Bobek")

    override fun create(){
        super.create()

        var terrainWidth = 100
        spawnX = terrainWidth * Tile.SIZE / 2F
        spawnY = Tile.SIZE.toFloat() - 6F

        terrain = Terrain(terrainWidth)
        player = Player(spawnX, TexturesManager.textures["player"]!!)

        npcManager = NpcManager()
        buildingsManager = BuildingsManager()

        Hud.create()
    }

    override fun update(){
        npcManager.update()

        player.handleInput()
        player.checkCollisions(npcManager)

        Hud.update(player)

        //if(player.collisionBox.x < house.x + house.width && player.collisionBox.x + player.collisionBox.width > house.x ){

        if(player.collisionBox.overlaps(buildingsManager.mainHouse.boundingRectangle)){
            Hud.tenantsLabel.update("House has ${buildingsManager.mainHouse.tenants} tenants")
        } else Hud.tenantsLabel.update("")

        for(npc in npcManager.npcList) {
            if(npc.boundingRectangle.overlaps(buildingsManager.mainHouse.boundingRectangle) && npc.currentState != NpcState.COLLECTING){
                npc.currentState = NpcState.COLLECTING

                buildingsManager.mainHouse.tenants++
            }
        }

        //TODO: Add timer
        if((0..100).random() == 50) {
            npcManager.addNpc(names.get((0..names.size).random()), (0..terrain.tiles.size * Tile.SIZE).random().toFloat(),
                    Tile.SIZE.toFloat() - 4F, TexturesManager.textures["villager"]!!)
        }
    }

    override fun render(){
        super.render()

        this.clearScreen(0F, 0.5F, 1F)
        batch.projectionMatrix = player.camera.combined

        staticBatch.begin().apply {
            staticBatch.draw(TexturesManager.textures["background"], 0F, 0F)

            staticBatch.end()
        }

        batch.begin().apply {
            terrain.renderOther(batch)

            batch.end()
        }

        staticBatch.begin().apply {
            //TODO: Add water shader
            staticBatch.draw(TexturesManager.textures["water"], 0F, -23F)
            staticBatch.end()
        }

        batch.begin().apply {
            npcManager.render(batch)
            buildingsManager.render(batch)
            terrain.render(batch)

            batch.end()
        }

        staticBatch.begin().apply {
            player.draw(staticBatch)

            staticBatch.end()
        }

        Hud.render()
    }

    override fun dispose(){
        super.dispose()
    }
}