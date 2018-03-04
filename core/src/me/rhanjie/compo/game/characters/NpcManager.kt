package me.rhanjie.compo.game.characters

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class NpcManager {
    var npcList = mutableListOf<Npc>()

    fun addNpc(name: String, x: Float, y: Float, texture: Texture) {
        npcList.add(Npc(name, x, y, texture))
    }

    fun update(){
        for(npc in npcList)
            npc.update()
    }

    fun render(batch: SpriteBatch) {
        for(npc in npcList) {
            batch.draw(npc, npc.x, npc.y)

            npc.render(batch)
        }
    }
}