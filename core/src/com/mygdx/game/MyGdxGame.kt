package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.mygdx.game.screens.TitleScreen

class MyGdxGame : Game() {
    companion object {
        lateinit var skin: Skin
        lateinit var textureAtlas: TextureAtlas
    }

    override fun create() {
        skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
        textureAtlas = TextureAtlas()
        textureAtlas.addRegion("note", TextureRegion(Texture("note.png")))
        this.setScreen(TitleScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        skin.dispose()
    }
}