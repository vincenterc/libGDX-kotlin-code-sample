package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame
import com.mygdx.game.actors.Musician
import com.mygdx.game.actors.MusicianNote

class GameScreen(val game: MyGdxGame) : Screen {
    private var stage: Stage = Stage(ScreenViewport())

    init {
        var musician = Musician()
        musician.x = Gdx.graphics.width / 12f
        stage.addActor(musician)

        var musicianNote = MusicianNote()
        musicianNote.setPosition(Gdx.graphics.width * 3f / 5f, Gdx.graphics.height * 1f / 5f)
        musicianNote.addAction(Actions.repeat(-1, Actions.sequence(Actions.moveTo(Gdx.graphics.width * 3f / 5f, Gdx.graphics.height * 3f / 5f, 2f, Interpolation.sine), Actions.moveTo(Gdx.graphics.width * 3f / 5f, Gdx.graphics.height * 1f / 5f, 2f, Interpolation.sine))))
        stage.addActor(musicianNote)
    }

    override fun show() {
        Gdx.app.log("MainScreen", "show")
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
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