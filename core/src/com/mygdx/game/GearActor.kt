package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.FixtureDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.ui.Image

class GearActor(
    world: World, pos_x: Float, pos_y: Float, width: Float, height: Float
) : Image(Texture(Gdx.files.internal("gear.png"))) {
    private var body: Body

    init {
        this.setSize(width, height)
        this.setPosition(pos_x, pos_y)

        var loader = BodyEditorLoader(Gdx.files.internal("box2d_scene.json"))

        var bd = BodyDef()
        bd.position.set(Gdx.graphics.width / 2f, Gdx.graphics.height / 2f)
        bd.type = BodyDef.BodyType.KinematicBody
        bd.position.x = this.x
        bd.position.y = this.y
        body = world.createBody(bd)

        var fd = FixtureDef()
        fd.density = 1f
        fd.friction = 0.5f
        fd.restitution = 0.3f

        var scale = this.width
        loader.attachFixture(body, "gear", fd, scale)

        this.setOrigin(this.width / 2f, this.height / 2f)
        body.angularVelocity = 1f
        body.userData = this
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }

    override fun act(delta: Float) {
        super.act(delta)

        this.rotation = body.angle * MathUtils.radiansToDegrees
        this.setPosition(body.position.x - this.width / 2f, body.position.y - this.height / 2f)
    }
}