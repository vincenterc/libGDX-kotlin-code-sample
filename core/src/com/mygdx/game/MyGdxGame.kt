package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MyGdxGame : ApplicationAdapter() {
    private lateinit var stage: Stage;

    override fun create() {
        stage = Stage(ScreenViewport())
        
        var helpGuides = 12;
        var rowHeight = Gdx.graphics.width / 12;
        var colWidth = Gdx.graphics.width / 12;
        addBackgroundGuide(helpGuides)

        var label1Style = Label.LabelStyle()
        var myFont = BitmapFont(Gdx.files.internal("bitmapfont/Amble-Regular-26.fnt"))
        label1Style.font = myFont
        label1Style.fontColor = Color.RED

        var label1 = Label("Title (BitmapFont)", label1Style)
        label1.setSize(Gdx.graphics.width.toFloat(), rowHeight.toFloat())
        label1.setPosition(0f, (Gdx.graphics.height - rowHeight * 2).toFloat())
        label1.setAlignment(Align.center)
        stage.addActor(label1)

        var generator = FreeTypeFontGenerator(Gdx.files.internal("truetypefont/Amble-Light.ttf"))
        var parameter = FreeTypeFontGenerator.FreeTypeFontParameter()
        parameter.size = 30
        parameter.borderWidth = 1f
        parameter.color = Color.YELLOW
        parameter.shadowOffsetX = 3
        parameter.shadowOffsetY = 3
        parameter.shadowColor = Color(0f, 0.5f, 0f, 0.75f)
        var font30 = generator.generateFont(parameter)
        generator.dispose()

        var label2Style = Label.LabelStyle()
        label2Style.font = font30

        var label2 = Label("True Type Font (.ttf) - Gdx FreeType", label2Style)
        label2.setSize((Gdx.graphics.width / helpGuides * 5).toFloat(), rowHeight.toFloat())
        label2.setPosition((colWidth * 2).toFloat(), (Gdx.graphics.height - rowHeight * 4).toFloat())
        stage.addActor(label2)

        var mySkin = Skin(Gdx.files.internal("skin/glassy-ui.json"))

        var label3 = Label("This is a Label (skin) on 5 columns", mySkin, "black")
        label3.setSize((Gdx.graphics.width / helpGuides * 5).toFloat(), rowHeight.toFloat())
        label3.setPosition((colWidth * 2).toFloat(), (Gdx.graphics.height - rowHeight * 6).toFloat())
        stage.addActor(label3)

        var label4 = Label("This is a Label (skin) with a 5 columns width but WITH wrap", mySkin, "black")
        label4.setSize((Gdx.graphics.width / helpGuides * 5).toFloat(), rowHeight.toFloat())
        label4.setPosition((colWidth * 2).toFloat(), (Gdx.graphics.height - rowHeight * 7).toFloat())
        label4.wrap = true
        stage.addActor(label4)

    }

    fun addBackgroundGuide(columns: Int) {
        var texture = Texture(Gdx.files.internal("background.jpg"))
        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)

        var textureRegion = TextureRegion(texture)
        textureRegion.setRegion(0, 0, texture.width * columns, texture.width * columns)
        var background = Image(textureRegion)
        background.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.width.toFloat())
        background.setPosition(0f, Gdx.graphics.height - background.height)
        stage.addActor(background)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}