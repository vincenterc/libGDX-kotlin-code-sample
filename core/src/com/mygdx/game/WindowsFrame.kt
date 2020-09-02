package com.mygdx.game

import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.PolygonShape
import com.badlogic.gdx.physics.box2d.World

class WindowsFrame(world: World, width: Float, height: Float) {
    private var body: Body

    init {
        var bd = BodyDef()
        bd.position.set(-20f, -5f)
        bd.type = BodyDef.BodyType.StaticBody

        body = world.createBody(bd)

        var groundBox = PolygonShape()
        groundBox.setAsBox(40f, 1f)
        body.createFixture(groundBox, 0f)
        body.userData = this
    }
}