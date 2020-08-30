package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.actions.*
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ScreenViewport
import kotlin.properties.Delegates

class MyGdxGame : ApplicationAdapter() {
    private lateinit var stage: Stage;
    private lateinit var lapsLabel: Label;
    private lateinit var lapsLabelContainer: Group;

    companion object {
        var lapsCount by Delegates.notNull<Int>()
    }

    override fun create() {
        lapsCount = 3
        stage = Stage(ScreenViewport())
        var skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))

        var texture = Texture(Gdx.files.internal("image.jpg"))

        var xLeft: Float = Gdx.graphics.width / 3f - texture.width / 2f
        var xRight: Float = Gdx.graphics.width * 2f / 3f - texture.width / 2f
        var yTop: Float = Gdx.graphics.height * 2f / 3f - texture.height / 2f
        var yBottom: Float = Gdx.graphics.height / 3f - texture.height / 2f

        var image1 = Image(texture)
        image1.setPosition(xLeft, yTop)
        image1.setOrigin(image1.width / 2f, image1.height / 2f)
        stage.addActor(image1)

        var topLeftRightParallelAction = ParallelAction()
        topLeftRightParallelAction.addAction(Actions.moveTo(xRight, yTop, 1f, Interpolation.exp5Out))
        topLeftRightParallelAction.addAction(Actions.scaleTo(2f, 2f, 1f, Interpolation.exp5Out))

        var moveBottomRightAction = MoveToAction()
        moveBottomRightAction.setPosition(xRight, yBottom)
        moveBottomRightAction.duration = 1f
        moveBottomRightAction.interpolation = Interpolation.smooth

        var updateLapCountAction = RunnableAction()
        updateLapCountAction.runnable = Runnable { updateLapsCount() }


        var bottomLeftRightParallelAction = ParallelAction()
        bottomLeftRightParallelAction.addAction(Actions.moveTo(xLeft, yBottom, 1f, Interpolation.sineOut))
        bottomLeftRightParallelAction.addAction(Actions.scaleTo(1f, 1f, 1f))

        var leftBottomTopParallelAction = ParallelAction()
        leftBottomTopParallelAction.addAction(Actions.moveTo(xLeft, yTop, 1f, Interpolation.swingOut))
        leftBottomTopParallelAction.addAction(Actions.rotateBy(90f, 1f))

        var overallSequence = SequenceAction()
        overallSequence.addAction(updateLapCountAction)
        overallSequence.addAction(topLeftRightParallelAction)
        overallSequence.addAction(moveBottomRightAction)
        overallSequence.addAction(bottomLeftRightParallelAction)
        overallSequence.addAction(leftBottomTopParallelAction)

        var loopNbrAction = RepeatAction()
        loopNbrAction.count = lapsCount
        loopNbrAction.action = overallSequence

        lapsLabel = Label("Loop :", skin, "big-black")
        lapsLabel.setPosition(0f, 0f)
        lapsLabel.setSize(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        lapsLabel.setAlignment(Align.center)

        lapsLabelContainer = Group()
        lapsLabelContainer.addActor(lapsLabel)
        lapsLabelContainer.setOrigin(lapsLabel.width / 2, lapsLabel.height / 2)

        stage.addActor(lapsLabelContainer)

        var completedAction = RunnableAction()
        completedAction.runnable = Runnable { finished() }

        image1.addAction(Actions.sequence(loopNbrAction, completedAction))
    }

    private fun updateLapsCount() {
        lapsCount--
        lapsLabelContainer.setScale(0f)
        var fadingSequenceAction = SequenceAction()
        fadingSequenceAction.addAction(Actions.fadeIn(1f))

        var parallelAction = ParallelAction()

        lapsLabel.setText("Laps : ${lapsCount + 1}")
        fadingSequenceAction.addAction(Actions.fadeOut(2f))
        parallelAction.addAction(Actions.scaleBy(5f, 5f, 4f))

        parallelAction.addAction(fadingSequenceAction)
        lapsLabelContainer.addAction(parallelAction)
    }

    private fun finished() {
        lapsLabelContainer.setScale(0f)
        var fadingSequenceAction = SequenceAction()
        fadingSequenceAction.addAction(Actions.fadeIn(1f))

        var parallelAction = ParallelAction()

        lapsLabel.setText("Finished")
        parallelAction.addAction(Actions.rotateBy(360f, 2f))
        parallelAction.addAction(Actions.scaleBy(1.2f, 1.2f, 3f, Interpolation.bounce))

        parallelAction.addAction(fadingSequenceAction)
        lapsLabelContainer.addAction(parallelAction)
    }

    override fun render() {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        stage.act()
        stage.draw()
    }
}