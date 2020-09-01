package com.mygdx.game.bodies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Floor(
    world: World,
    pos_x: Float,
    pos_y: Float,
    width: Float,
    height: Float,
    angle: Float
) : Image(Texture(Gdx.files.internal("wood.jpg"))) {
    private var body: Body

    init {
        this.setSize(width, height)
        this.setOrigin(this.width / 2, this.height / 2)
        this.rotateBy(angle)
        this.setPosition(pos_x, pos_y)

        var groundBodyDef = BodyDef()
        groundBodyDef.position.set(Vector2(pos_x, pos_y))

        body = world.createBody(groundBodyDef)

        var groundBox = PolygonShape()
        groundBox.setAsBox(this.width / 2f, this.height / 2f)

        body.setTransform(
            this.x + this.width / 2f,
            this.y + this.height / 2f,
            angle * MathUtils.degreesToRadians
        )
        body.createFixture(groundBox, 0f)

        groundBox.dispose()
    }

    override fun act(delta: Float) {
        super.act(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }
}