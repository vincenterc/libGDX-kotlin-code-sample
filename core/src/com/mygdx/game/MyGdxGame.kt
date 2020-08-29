package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.viewport.ScreenViewport

class MyGdxGame : ApplicationAdapter() {
    private lateinit var stage: Stage

    override fun create() {
        stage = Stage(ScreenViewport())
        var texture = Texture(Gdx.files.internal("image.jpg"))

        var image1 = Image(texture)
        image1.setPosition(Gdx.graphics.width / 3 - image1.width / 2, Gdx.graphics.height * 2 / 3 - image1.height / 2)
        stage.addActor(image1)

        var image2 = Image(texture)
        image2.setPosition(Gdx.graphics.width * 2 / 3 - image2.width / 2, Gdx.graphics.height * 2 / 3 - image2.height / 2)
        image2.setOrigin(image2.width / 2, image2.height / 2)
        image2.rotateBy(45f)
        stage.addActor(image2)

        var image3 = Image(texture)
        image3.setSize(texture.width / 2f, texture.height / 2f)
        image3.setPosition(Gdx.graphics.width / 3 - image3.width / 2, Gdx.graphics.height / 3 - image3.height / 2)
        stage.addActor(image3)

        texture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)
        var textureRegion = TextureRegion(texture)
        textureRegion.setRegion(0, 0, texture.width * 8, texture.height * 4)
        var image4 = Image(textureRegion)
        image4.setSize(200f, 100f)
        image4.setPosition(Gdx.graphics.width * 2 / 3 - image4.width / 2, Gdx.graphics.height / 3 - image4.height / 2)
        stage.addActor(image4)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}