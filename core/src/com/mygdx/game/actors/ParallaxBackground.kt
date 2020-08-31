package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.utils.Array

class ParallaxBackground(val layers: Array<Texture>) : Actor() {
    private var scroll: Int = 0
    private val LAYER_SPEED_DIFFERENCE = 2

    var pX: Float = 0f
    var pY: Float = 0f
    var pWidth: Float = Gdx.graphics.width.toFloat()
    var pHeight: Float = Gdx.graphics.height.toFloat()
    var pScaleX: Float = 1f
    var pScaleY: Float = 1f

    var originX: Int = 0
    var originY: Int = 0
    var rotation: Int = 0
    var srcX: Int = 0
    var srcY: Int = 0

    var flipX: Boolean = false
    var flipY: Boolean = false

    private var speed: Int = 0

    init {
        for (layer in layers) {
            layer.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.MirroredRepeat)
        }
    }

    fun setSpeed(newSpeed: Int) {
        speed = newSpeed
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha)

        scroll += speed
        for ((index, layer) in layers.withIndex()) {
            srcX = scroll + index * LAYER_SPEED_DIFFERENCE * scroll
            batch.draw(layer, pX, pY, originX.toFloat(), originY.toFloat(), pWidth, pHeight, pScaleX, pScaleY, rotation.toFloat(), srcX, srcY, layer.width, layer.height, flipX, flipY)
        }
    }
}