package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Button
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
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

        var title = Label("Buttons with Skins", mySkin, "big-black")
        title.setSize(Gdx.graphics.width.toFloat(), (rowHeight * 2).toFloat())
        title.setPosition(0f, (Gdx.graphics.height - rowHeight * 2).toFloat())
        title.setAlignment(Align.center)
        stage.addActor(title)

        var button1 = Button(mySkin, "small")
        button1.setSize((colWidth * 4).toFloat(), rowHeight.toFloat())
        button1.setPosition(colWidth.toFloat(), (Gdx.graphics.height - rowHeight * 3).toFloat())
        button1.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                outputLabel.setText("Press a Button")
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                outputLabel.setText("Pressed Button")
                return true
            }
        })
        stage.addActor(button1)

        var button2 = TextButton("Text Button", mySkin, "small")
        button2.setSize((colWidth * 4).toFloat(), rowHeight.toFloat())
        button2.setPosition((colWidth * 7).toFloat(), (Gdx.graphics.height - rowHeight * 3).toFloat())
        button2.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                outputLabel.setText("Press a Button")
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                outputLabel.setText("Pressed Text Button")
                return true
            }
        })
        stage.addActor(button2)

        var button3 = ImageButton(mySkin)
        button3.setSize((colWidth * 4).toFloat(), rowHeight * 2.toFloat())
        button3.style.imageUp = TextureRegionDrawable(TextureRegion(Texture(Gdx.files.internal("switch_off.png"))))
        button3.style.imageDown = TextureRegionDrawable(TextureRegion(Texture(Gdx.files.internal("switch_on.png"))))
        button3.setPosition((colWidth).toFloat(), (Gdx.graphics.height - rowHeight * 6).toFloat())
        button3.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                outputLabel.setText("Press a Button")
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                outputLabel.setText("Pressed Image Button")
                return true
            }
        })
        stage.addActor(button3)

        var button4 = ImageTextButton("ImageText Btn", mySkin, "small")
        button4.setSize((colWidth * 4).toFloat(), (rowHeight * 2).toFloat())
        button4.style.imageUp = TextureRegionDrawable(TextureRegion(Texture(Gdx.files.internal("switch_off.png"))))
        button4.style.imageDown = TextureRegionDrawable(TextureRegion(Texture(Gdx.files.internal("switch_on.png"))))
        button4.setPosition((colWidth * 7).toFloat(), (Gdx.graphics.height - rowHeight * 6).toFloat())
        button4.addListener(object : InputListener() {
            override fun touchUp(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int) {
                outputLabel.setText("Press a Button")
            }

            override fun touchDown(event: InputEvent?, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                outputLabel.setText("Pressed Image Text Button")
                return true
            }
        })
        stage.addActor(button4)

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