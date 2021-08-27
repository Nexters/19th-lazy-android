package com.igalata.bubblepicker.physics

import com.igalata.bubblepicker.sqr
import org.jbox2d.common.Vec2
import org.jbox2d.dynamics.World
import java.util.*

object Engine {

    var maxSelectedCount: Int? = null
    var radius = 50
        set(value) {
            field = value
            bubbleRadius = interpolate(0.1f, 0.25f, value / 100f)
            gravity = interpolate(20f, 80f, value * 1.5f)
        }
    private var bubbleRadius = 0.17f
    private var world = World(Vec2(0f, -1f), false)
    private const val step = 0.0005f
    private val bodies: ArrayList<CircleBody> = ArrayList()
    private var borders: ArrayList<Border> = ArrayList()
    private var scaleX = 0f
    private var scaleY = 0f
    private var gravity = 6f
    private var gravityCenter = Vec2(0f, -1f)
    private const val currentGravity: Float = 350f

    fun build(bodiesCount: Int, scaleX: Float, scaleY: Float): List<CircleBody> {
        val density = interpolate(0.8f, 0.2f, radius / 100f)
        for (i in 0 until bodiesCount) {
            val x =
                kotlin.random.Random.nextDouble(-1.0, 1.0) * (if (Random().nextBoolean()) -1 else 1)
            val y = (0.92 + kotlin.random.Random.nextDouble(0.0, 0.1)) / scaleY
            bodies.add(
                CircleBody(
                    world,
                    Vec2(x.toFloat(), y.toFloat()),
                    bubbleRadius * scaleX,
                    (bubbleRadius * scaleX) * 1.3f,
                    density
                )
            )
        }
        this.scaleX = scaleX
        this.scaleY = scaleY
        createBorders()
        return bodies
    }

    fun move() {
        world.step(step, 11, 11)
        bodies.forEach { move(it) }
    }

    fun clear() {
        world = World(Vec2(0f, -1f), false)
        borders.forEach { if (world.isLocked) world.destroyBody(it.itemBody) }
        bodies.forEach { if (world.isLocked) world.destroyBody(it.physicalBody) }
        borders.clear()
        bodies.clear()
    }

    private fun createBorders() {
        borders = arrayListOf(
            Border(world, Vec2(0.5f, -0.5f / scaleY), Border.VERTICAL),
            Border(world, Vec2(0.5f, -0.5f / scaleY), Border.VERTICAL),
            Border(world, Vec2(0f, -0.5f / scaleY), Border.HORIZONTAL)
        )
    }

    private fun move(body: CircleBody) {
        body.physicalBody.apply {
            val direction = gravityCenter.sub(position)
            val distance = direction.length()
            val gravity = currentGravity
            if (distance > step * 200) {
                applyForce(direction.mul(gravity / distance.sqr()), position)
            }
        }
    }

    private fun interpolate(start: Float, end: Float, f: Float) = start + f * (end - start)

}