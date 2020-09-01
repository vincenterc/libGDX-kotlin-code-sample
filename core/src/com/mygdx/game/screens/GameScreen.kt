package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.Touchable
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame
import com.mygdx.game.bodies.Floor
import com.mygdx.game.bodies.MusicalNote

class GameScreen(game: MyGdxGame) : Screen {
    private val stage = Stage(ScreenViewport())
    private val world = World(Vector2(0f, -1000f), true)
    private val debugRenderer = Box2DDebugRenderer()

    init {
        var musicalNote = MusicalNote(world, Gdx.graphics.width / 4f, Gdx.graphics.height.toFloat())
        stage.addActor(musicalNote)
        musicalNote.addListener(object : InputListener() {
            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                Gdx.app.log("touchDown", "X: $x Y: $y")
                return true
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.log("touchUp", "")
            }
        })
        musicalNote.touchable = Touchable.enabled


        stage.addActor(
            Floor(
                world, 0f, Gdx.graphics.height / 3f,
                Gdx.graphics.width * 2f / 3f, Gdx.graphics.height / 10f,
                -30f
            )
        )
    }

    override fun show() {
        Gdx.app.log("MainScreen", "show")
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()

        debugRenderer.render(world, stage.camera.combined)
        world.step(Gdx.graphics.deltaTime, 6, 2)
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