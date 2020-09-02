package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Ball(var world: World, pos_x: Float, pos_y: Float) : Image(Texture(Gdx.files.internal("bubble.png"))) {
    private var body: Body
    private var delete = false

    init {
        this.setSize(0.3f, 0.3f)
        this.setPosition(pos_x, pos_y)

        var bd = BodyDef()
        bd.position.set(this.x, this.y)
        bd.type = BodyDef.BodyType.DynamicBody

        body = world.createBody(bd)

        var circle = CircleShape()
        circle.radius = this.width / 2f

        var fd = FixtureDef()
        fd.density = 10f
        fd.friction = 0.5f
        fd.restitution = 0.3f
        fd.shape = circle

        var fixture = body.createFixture(fd)
        body.userData = this

        this.setOrigin(this.width / 2f, this.height / 2f)
        circle.dispose()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

    override fun act(delta: Float) {
        super.act(delta)
        if (delete) {
            world.destroyBody(body)
            this.remove()
        }
        this.rotation = body.angle * MathUtils.radiansToDegrees
        this.setPosition(body.position.x - this.width / 2f, body.position.y - this.height / 2f)
    }

    fun eliminate() {
        delete = true
    }
}