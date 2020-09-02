package com.mygdx.game

import com.badlogic.gdx.physics.box2d.*

class WindowsFrame(world: World, width: Float, height: Float) {
    private var body: Body

    init {
        var bd = BodyDef()
        bd.position.set(-width * 5, -height / 2)
        bd.type = BodyDef.BodyType.StaticBody

        var groundBox = PolygonShape()
        groundBox.setAsBox(width * 10f, 0.1f)

        var fixtureDef = FixtureDef()
        fixtureDef.isSensor = true
        fixtureDef.shape = groundBox

        body = world.createBody(bd)
        body.userData = this
        body.createFixture(fixtureDef)
    }
}