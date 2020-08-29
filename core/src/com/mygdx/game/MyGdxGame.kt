package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MyGdxGame : ApplicationAdapter() {
    private lateinit var stage: Stage;
    private lateinit var outputLabel: Label;

    override fun create() {
        stage = Stage(ScreenViewport())
        Gdx.input.inputProcessor = stage

        var Help_Guides = 12
        var rowHeight = Gdx.graphics.width / 12
        var colWidth = Gdx.graphics.width / 12

        var mySkin = Skin(Gdx.files.internal("skin/glassy-ui.json"))

        outputLabel = Label("Press a  Button", mySkin, "black")
        outputLabel.setSize(Gdx.graphics.width.toFloat(), rowHeight.toFloat())
        outputLabel.setPosition(0f, rowHeight.toFloat())
        outputLabel.setAlignment(Align.center)
        stage.addActor(outputLabel)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}