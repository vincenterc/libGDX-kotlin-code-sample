package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.physics.box2d.graphics.ParticleEmitterBox2D
import com.badlogic.gdx.scenes.scene2d.Actor

class FireEmitter(world: World) : Actor() {
    private var fireEmitter: ParticleEffect

    init {
        var textureAtlas = TextureAtlas()
        textureAtlas.addRegion("particle", TextureRegion(Texture(Gdx.files.internal("particle.png"))))

        fireEmitter = ParticleEffect()
        fireEmitter.load(Gdx.files.internal("continous.p"), textureAtlas)
        fireEmitter.emitters.add(ParticleEmitterBox2D(world, fireEmitter.emitters.first()))
        fireEmitter.emitters.removeIndex(0)
        fireEmitter.setPosition(5f, 2f)
        fireEmitter.scaleEffect(0.013f)
        fireEmitter.start()
    }

    override fun act(delta: Float) {
        super.act(delta)
        fireEmitter.update(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        fireEmitter.draw(batch)
    }
}