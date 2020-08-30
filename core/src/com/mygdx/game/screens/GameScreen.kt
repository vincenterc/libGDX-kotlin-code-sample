package com.mygdx.game.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.game.MyGdxGame
import java.time.Instant
import kotlin.math.abs
import kotlin.math.cos
import kotlin.properties.Delegates

class GameScreen(val game: MyGdxGame) : Screen {
    private var stage: Stage = Stage(ScreenViewport())
    private var camera: OrthographicCamera

    private val startX = 1100
    private val startY = 1225

    private val endX = 2350
    private val endY = 600

    private var minAltitude = 0.5f
    private var maxAltitude = 2.5f

    private var percent by Delegates.notNull<Float>()
    private var counter by Delegates.notNull<Float>()
    private var startTime by Delegates.notNull<Long>()
    private val animationDuration = 15000f

    init {
        var map = Image(Texture("map.jpg"))
        stage.addActor(map)

        camera = stage.viewport.camera as OrthographicCamera
        camera.translate(startX.toFloat(), startY.toFloat())

        counter = 0f
        startTime = Instant.now().toEpochMilli()
    }

    override fun show() {
    }

    override fun render(delta: Float) {
        var secondFromStart = Instant.now().toEpochMilli() - startTime
        percent = (secondFromStart % animationDuration) / animationDuration
        percent = (cos(percent * Math.PI * 2f) / 2f + 0.5f).toFloat()
        Gdx.app.log("render", "secondFromStart: $secondFromStart, %: $percent")
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        moveCamera()
        stage.act()
        stage.draw()
    }

    private fun moveCamera() {
        var currentX: Float = startX + (endX - startX) * percent
        var currentY: Float = startY + (endY - startY) * percent
        var percentZ: Float = abs(percent - 0.5f) * 2f
        var currentZ: Float = maxAltitude - (maxAltitude - minAltitude) * percentZ

        camera.position.x = currentX
        camera.position.y = currentY
        camera.zoom = currentZ
        camera.update()
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