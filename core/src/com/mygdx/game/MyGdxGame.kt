package com.mygdx.game

import box2dLight.PointLight
import box2dLight.RayHandler
import com.badlogic.gdx.Application
import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import kotlin.random.Random

class MyGdxGame : ApplicationAdapter() {
    lateinit var batch: SpriteBatch
    private lateinit var world: World
    private lateinit var stage: Stage
    private lateinit var debugRenderer: Box2DDebugRenderer

    private lateinit var rayHandler: RayHandler

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG

        world = World(Vector2(0f, -3f), true)
        world.setContactListener(B2dContactListener())

        batch = SpriteBatch()

        var ratio: Float = Gdx.graphics.width.toFloat() / Gdx.graphics.height.toFloat()

        stage = Stage(ScreenViewport())
        stage.camera.position.set(0f, 0f, 10f)
        stage.camera.lookAt(0f, 0f, 0f)
        stage.camera.viewportWidth = 10f
        stage.camera.viewportHeight = 10f / ratio

        Gdx.input.inputProcessor = stage

        debugRenderer = Box2DDebugRenderer()

        var gearActor = GearActor(world, 0f, 0f, 3f, 3f)
        stage.addActor(gearActor)

        WindowsFrame(world, stage.camera.viewportWidth, stage.camera.viewportHeight)

        rayHandler = RayHandler(world)
        rayHandler.setAmbientLight(0.1f, 0.1f, 0.1f, 1f)
        rayHandler.setBlurNum(3)


        var pl = PointLight(rayHandler, 128, Color(0.2f, 1f, 1f, 1f), 10f, -5f, 2f)
        var pl2 = PointLight(rayHandler, 128, Color(1f, 0f, 1f, 1f), 10f, 5f, 2f)

        rayHandler.setShadows(true)
        pl.isStaticLight = false
        pl.isSoft = true

        BallGenerator.setup(stage, world)
        stage.addActor(FireEmitter(world))
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()

        world.step(Gdx.graphics.deltaTime, 6, 2)

        BallGenerator.emit()

        rayHandler.setCombinedMatrix(stage.camera.combined, 0f, 0f, 1f, 1f)
        rayHandler.updateAndRender()
    }

    override fun dispose() {
        batch.dispose()
        stage.dispose()
        rayHandler.dispose()
    }
}