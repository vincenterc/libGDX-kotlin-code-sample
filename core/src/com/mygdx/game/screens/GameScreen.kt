package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame
import com.mygdx.game.actors.ParallaxBackground

class GameScreen(val game: MyGdxGame) : Screen {
    private var stage: Stage = Stage(ScreenViewport())
    private var camera: OrthographicCamera

    init {
        camera = stage.viewport.camera as OrthographicCamera

        var textures = Array<Texture>()
        for (i in 1..6) {
            textures.add(Texture(Gdx.files.internal("parallax/img$i.png")))
            textures[textures.size - 1].setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)
        }

        val parallaxBackground = ParallaxBackground(textures)
        parallaxBackground.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        parallaxBackground.setSpeed(1)
        stage.addActor(parallaxBackground)
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        stage.dispose()
    }
}