package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.mygdx.game.screens.TitleScreen

class MyGdxGame : Game() {
    companion object {
        lateinit var skin: Skin
    }

    override fun create() {
        skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
        this.setScreen(TitleScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        skin.dispose()
    }
}