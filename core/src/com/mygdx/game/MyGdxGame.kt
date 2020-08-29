package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MyGdxGame : ApplicationAdapter() {
    private lateinit var stage: Stage;

    override fun create() {
        stage = Stage(ScreenViewport())
        var Help_Guides = 12;
        var row_height = Gdx.graphics.width / 12;
        var col_width = Gdx.graphics.height / 12;
        addBackgroundGuide(Help_Guides)
    }

    fun addBackgroundGuide(columns: Int) {
        var texture = Texture(Gdx.files.internal("background.jpg"))
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)

        var textureRegion = TextureRegion(texture)
        textureRegion.setRegion(0, 0, texture.width * columns, texture.width * columns)
        var background = Image(textureRegion)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.width.toFloat())
        background.setPosition(0f, Gdx.graphics.height - background.height)
        stage.addActor(background)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}