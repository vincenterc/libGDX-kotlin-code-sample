package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MyGdxGame : ApplicationAdapter() {
    private lateinit var stage: Stage;

    override fun create() {
        stage = Stage(ScreenViewport())
        var texture = Texture(Gdx.files.internal("image.jpg"))

        var xLeft: Float = Gdx.graphics.width / 3f - texture.width / 2f
        var xRight: Float = Gdx.graphics.width * 2f / 3f - texture.width / 2f
        var yTop: Float = Gdx.graphics.height * 2f / 3f - texture.height / 2f
        var yBottom: Float = Gdx.graphics.height / 3f - texture.height / 2f

        var image1 = Image(texture)
        image1.setPosition(xLeft, yTop)
        image1.setOrigin(image1.width / 2f, image1.height / 2f)
        stage.addActor(image1)

        var topLeftRightParallelAction = ParallelAction()
        topLeftRightParallelAction.addAction(Actions.moveTo(xRight, yTop, 1f, Interpolation.exp5Out))
        topLeftRightParallelAction.addAction(Actions.scaleTo(2f, 2f, 1f, Interpolation.exp5Out))

        var moveBottomRightAction = MoveToAction()
        moveBottomRightAction.setPosition(xRight, yBottom)
        moveBottomRightAction.duration = 1f
        moveBottomRightAction.interpolation = Interpolation.smooth

        image1.addAction(moveBottomRightAction)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}