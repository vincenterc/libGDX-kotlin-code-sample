package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import kotlin.random.Random

object BallGenerator {
    private const val MAX_NBR = 30
    private var ballNbr: Int = 0

    private lateinit var world: World
    private lateinit var stage: Stage

    private var ballExplosionPool: ParticleEffectPool

    init {
        var textureAtlas = TextureAtlas()
        textureAtlas.addRegion("particle", TextureRegion(Texture(Gdx.files.internal("particle.png"))))

        var explosionEffect = ParticleEffect()
        explosionEffect.load(Gdx.files.internal("particles.p"), textureAtlas)
        ballExplosionPool = ParticleEffectPool(explosionEffect, MAX_NBR * 2, MAX_NBR * 2)
    }

    fun setup(aStage: Stage, aWorld: World) {
        stage = aStage
        world = aWorld
    }

    fun emit() {
        if (ballNbr < MAX_NBR) {
            var ball = Ball(world, (Random.nextInt(60) - 30) / 10f, 9f)
            stage.addActor(ball)
            ballNbr++
            Gdx.app.debug("generateBalls", "Balls: $ballNbr")
        }
    }

    fun explode(ball: Ball) {
        var effect = ballExplosionPool.obtain()
        ball.explode(effect)
        ballNbr--
    }
}