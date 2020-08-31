package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mygdx.game.MyGdxGame

class Musician : Image(Texture(Gdx.files.internal("musician.png"))) {
    var effect: ParticleEffect = ParticleEffect()

    init {
        effect.load(Gdx.files.internal("musician.p"), MyGdxGame.textureAtlas)
        effect.start()
        effect.setPosition(this.width + this.x, this.height + this.y)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        effect.draw(batch)
    }

    override fun act(delta: Float) {
        super.act(delta)
        effect.update(delta)
    }

    override fun setX(x: Float) {
        super.setX(x)
        effect.setPosition(this.width + this.x, this.height + this.y)
    }
}