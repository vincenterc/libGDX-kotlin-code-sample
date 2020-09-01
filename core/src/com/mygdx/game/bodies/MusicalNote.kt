package com.mygdx.game.bodies

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mygdx.game.MyGdxGame

class MusicalNote(
    world: World, pos_x: Float, pos_y: Float
) : Image(Texture(Gdx.files.internal("note.jpg"))) {
    private var effect: ParticleEffect
    private var body: Body

    init {
        this.setPosition(pos_x, pos_y)

        effect = ParticleEffect()
        effect.load(Gdx.files.internal("bubleNote.p"), MyGdxGame.textureAtlas)
        effect.start()
        effect.setPosition(this.width / 2f + this.x, this.height / 2f + this.y)

        var bodyDef = BodyDef()
        bodyDef.type = BodyDef.BodyType.DynamicBody
        bodyDef.position.set(pos_x, pos_y)

        body = world.createBody(bodyDef)

        var shape = PolygonShape()
        shape.setAsBox(this.width / 2f, this.height / 2f)

        var fixtureDef = FixtureDef()
        fixtureDef.shape = shape
        fixtureDef.density = 5f
        fixtureDef.friction = 0f
        fixtureDef.restitution = 1f
        var fixture = body.createFixture(fixtureDef)

        shape.dispose()
        this.setOrigin(this.width / 2f, this.height / 2f)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        effect.draw(batch)
    }

    override fun act(delta: Float) {
        super.act(delta)
        this.rotation = body.angle * MathUtils.radiansToDegrees

        this.setPosition(body.position.x - this.width / 2f, body.position.y - this.height / 2f)
        effect.setPosition(this.width / 2f + this.x, this.height / 2f + this.y)
        effect.update(delta)
    }
}