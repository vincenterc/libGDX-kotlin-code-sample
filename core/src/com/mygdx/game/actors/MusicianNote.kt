package com.mygdx.game.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.mygdx.game.MyGdxGame

class MusicianNote : Image(Texture(Gdx.files.internal("note.png"))) {
    var effect: ParticleEffect = ParticleEffect()

    init {
        effect.load(Gdx.files.internal("bubleNote.p"), MyGdxGame.textureAtlas)
        effect.start()
        effect.setPosition(this.width / 2f + this.x, this.height / 2f + this.y)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
        effect.draw(batch)
    }

    override fun act(delta: Float) {
        super.act(delta)
        effect.setPosition(this.width / 2f + this.x, this.height / 2f + this.y)
        effect.update(delta)
    }
}