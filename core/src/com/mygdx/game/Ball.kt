package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.graphics.ParticleEmitterBox2D
import com.badlogic.gdx.scenes.scene2d.ui.Image

class Ball(var world: World, pos_x: Float, pos_y: Float) : Image(Texture(Gdx.files.internal("bubble.png"))) {
    private var body: Body
    private var delete = false
    private lateinit var explosionEffect: ParticleEffect
    private var exploding = false

    init {
        this.setSize(0.5f, 0.5f)
        this.setPosition(pos_x, pos_y)

        var bd = BodyDef()
        bd.position.set(this.x, this.y)
        bd.type = BodyDef.BodyType.DynamicBody

        var circle = CircleShape()
        circle.radius = this.width / 2f

        var fd = FixtureDef()
        fd.density = 10f
        fd.friction = 0.5f
        fd.restitution = 0.6f
        fd.shape = circle

        body = world.createBody(bd)
        var fixture = body.createFixture(fd)
        body.userData = this

        this.setOrigin(this.width / 2f, this.height / 2f)
        circle.dispose()
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        if (exploding) {
            explosionEffect.draw(batch)
        }
    }

    override fun act(delta: Float) {
        super.act(delta)

        if (exploding) {
            explosionEffect.update(delta)

            if (explosionEffect.isComplete) {
                explosionEffect.dispose()
                world.destroyBody(body)
                this.delete = true
            }
        }

        this.rotation = body.angle * MathUtils.radiansToDegrees
        this.setPosition(body.position.x - this.width / 2f, body.position.y - this.height / 2f)

        if (delete) {
            this.remove()
        }
    }

    fun explode(effect: ParticleEffect) {
        explosionEffect = effect
        explosionEffect.emitters.add(ParticleEmitterBox2D(world, explosionEffect.emitters.first()))
        explosionEffect.emitters.removeIndex(0)
        explosionEffect.setPosition(this.x + this.width / 2f, this.y)
        explosionEffect.scaleEffect(0.01f)
        explosionEffect.start()

        exploding = true
    }
}