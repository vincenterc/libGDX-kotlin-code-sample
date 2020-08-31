package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Slider
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame

class GameScreen(private val game: MyGdxGame) : Screen {
    private val gameStage = Stage(ScreenViewport())
    private val uiStage = Stage(ScreenViewport())
    private var camera: OrthographicCamera

    private var slider: Slider
    private var multiplexer: InputMultiplexer

    init {
        camera = gameStage.viewport.camera as OrthographicCamera
        camera.translate(200f, 250f)
        camera.zoom = 1.55f

        multiplexer = InputMultiplexer()
        multiplexer.addProcessor(uiStage)
        multiplexer.addProcessor(gameStage)

        var map = Image(Texture(Gdx.files.internal("map.jpg")))
        map.addListener(object : ActorGestureListener() {
            override fun pan(event: InputEvent?, x: Float, y: Float, deltaX: Float, deltaY: Float) {
                super.pan(event, x, y, deltaX, deltaY)
                camera.position.x -= (deltaX * Gdx.graphics.density)
                camera.position.y -= (deltaY * Gdx.graphics.density)
            }
        })
        gameStage.addActor(map)

        var ring = Image(Texture(Gdx.files.internal("ring.png")))
        ring.setPosition(1100f, 1225f)
        gameStage.addActor(ring)

        var magnifier = Image(Texture(Gdx.files.internal("magnifier.png")))
        magnifier.setPosition(Gdx.graphics.width / 2f - magnifier.width / 4f, Gdx.graphics.height / 2f - magnifier.height / 2f)
        uiStage.addActor(magnifier)

        slider = Slider(1f, 2f, 0.01f, true, MyGdxGame.skin)
        slider.setAnimateInterpolation(Interpolation.smooth)
        slider.height = Gdx.graphics.height * 0.8f
        slider.setPosition(Gdx.graphics.width / 12f, Gdx.graphics.height / 10f)
        slider.value = 1.55f
        slider.addListener(object : InputListener() {
            override fun touchDragged(event: InputEvent?, x: Float, y: Float, pointer: Int) {
                camera.zoom = slider.value
                camera.update()

                super.touchDragged(event, x, y, pointer)
            }

            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                Gdx.app.log("up", "slider Value: ${slider.value}")
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                Gdx.app.log("down", "slider Value: ${slider.value}")

                return true
            }
        })
        uiStage.addActor(slider)
    }

    override fun show() {
        Gdx.app.log("MainScreen", "show")
        Gdx.input.inputProcessor = multiplexer
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        uiStage.act()
        gameStage.act()

        gameStage.draw()
        uiStage.draw()
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
        uiStage.dispose()
        gameStage.dispose()
    }
}