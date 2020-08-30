package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame

class OptionScreen(val game: MyGdxGame) : Screen {
    private var stage: Stage = Stage(ScreenViewport())

    init {
        var title = Label("Options Screen", MyGdxGame.gameSkin, "big-black")
        title.setAlignment(Align.center)
        title.y = Gdx.graphics.height * 2f / 3f
        title.width = Gdx.graphics.width.toFloat()
        stage.addActor(title)

        var backButton = TextButton("Go Back", MyGdxGame.gameSkin)
        backButton.width = Gdx.graphics.width / 2f
        backButton.setPosition(Gdx.graphics.width / 2f - backButton.width / 2f, Gdx.graphics.height / 4f - backButton.height / 2f)
        backButton.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                game.screen = TitleScreen(game)
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                return true
            }
        })
        stage.addActor(backButton)
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
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