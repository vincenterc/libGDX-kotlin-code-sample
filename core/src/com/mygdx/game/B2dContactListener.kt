package com.mygdx.game

import com.badlogic.gdx.physics.box2d.Contact
import com.badlogic.gdx.physics.box2d.ContactImpulse
import com.badlogic.gdx.physics.box2d.ContactListener
import com.badlogic.gdx.physics.box2d.Manifold

class B2dContactListener : ContactListener {
    override fun beginContact(contact: Contact) {
        var classA = contact.fixtureA.body.userData.javaClass.name
        var classB = contact.fixtureB.body.userData.javaClass.name

        if (classA.equals("com.mygdx.game.WindowsFrame", ignoreCase = true) &&
            classB.equals("com.mygdx.game.Ball", ignoreCase = true)
        ) {
            var a: Ball = contact.fixtureB.body.userData as Ball
            a.eliminate()
            MyGdxGame.ballNbr--
        } else if (classB.equals("com.mygdx.game.WindowFrame", ignoreCase = true) &&
                   classA.equals("com.mygdx.game.Ball", ignoreCase = true)
        ) {
            var a: Ball = contact.fixtureA.body.userData as Ball
            a.eliminate()
            MyGdxGame.ballNbr--
        }
    }

    override fun endContact(contact: Contact?) {
    }

    override fun preSolve(contact: Contact?, oldManifold: Manifold?) {
    }

    override fun postSolve(contact: Contact?, impulse: ContactImpulse?) {
    }
}