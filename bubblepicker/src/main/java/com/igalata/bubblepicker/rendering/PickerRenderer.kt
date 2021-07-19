package com.igalata.bubblepicker.rendering

import android.opengl.GLES20
import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import android.util.Log
import android.view.View
import com.igalata.bubblepicker.*
import com.igalata.bubblepicker.model.Color
import com.igalata.bubblepicker.model.PickerItem
import com.igalata.bubblepicker.physics.Engine
import com.igalata.bubblepicker.rendering.BubbleShader.A_POSITION
import com.igalata.bubblepicker.rendering.BubbleShader.A_UV
import com.igalata.bubblepicker.rendering.BubbleShader.U_BACKGROUND
import com.igalata.bubblepicker.rendering.BubbleShader.fragmentShader
import com.igalata.bubblepicker.rendering.BubbleShader.vertexShader
import java.nio.FloatBuffer
import java.util.*
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class PickerRenderer(val glView: View) : GLSurfaceView.Renderer {

    var backgroundColor: Color? = null

    var maxSelectedCount: Int? = null
        set(value) {
            Engine.maxSelectedCount = value
            field = value
        }

    var bubbleSize = 50
        set(value) {
            Engine.radius = value
            field = value
        }

    var listener: BubblePickerListener? = null
    lateinit var items: ArrayList<PickerItem>

    private var programId = 0
    private var verticesBuffer: FloatBuffer? = null
    private var uvBuffer: FloatBuffer? = null
    private var vertices: FloatArray? = null
    private var textureVertices: FloatArray? = null
    private var textureIds: IntArray? = null

    private val scaleX: Float
        get() = if (glView.width < glView.height) glView.height.toFloat() / glView.width.toFloat() else 1f
    private val scaleX2: Float
        get() = if (glView.width < glView.height) glView.height.toFloat() / glView.width.toFloat() else 1f

    private val scaleY: Float
        get() = if (glView.width < glView.height) 1f else glView.width.toFloat() / glView.height.toFloat()
    private val circles = ArrayList<Item>()

    override fun onSurfaceCreated(gl: GL10?, config: EGLConfig?) {
        glClearColor(
            backgroundColor?.red ?: 1f, backgroundColor?.green ?: 1f,
            backgroundColor?.blue ?: 1f, backgroundColor?.alpha ?: 1f
        )
        enableTransparency()
    }

    override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
        Log.d("tag1", "$width  $height")
        glViewport(0, 0, width, height)
        initialize()
    }

    override fun onDrawFrame(gl: GL10?) {
        calculateVertices()
        Engine.move()
        drawFrame()
    }

    fun initialize() {
        clear()
        Engine.build(items.size, scaleX, scaleY).forEachIndexed { index, body ->
            circles.add(Item(items[index], body))
        }
        if (textureIds == null) textureIds = IntArray(circles.size * 2)
        initializeArrays()
    }

    private fun initializeArrays() {
        vertices = FloatArray(circles.size * 8)
        textureVertices = FloatArray(circles.size * 8)
        circles.forEachIndexed { i, item -> initializeItem(item, i) }
        verticesBuffer = vertices?.toFloatBuffer()
        uvBuffer = textureVertices?.toFloatBuffer()
    }

    private fun initializeItem(item: Item, index: Int) {
        initializeVertices(item, index)
        textureVertices?.passTextureVertices(index)
        item.bindTextures(textureIds ?: IntArray(0), index)
    }

    private fun calculateVertices() {
        circles.forEachIndexed { i, item -> initializeVertices(item, i) }
        vertices?.forEachIndexed { i, float -> verticesBuffer?.put(i, float) }
    }

    private fun initializeVertices(body: Item, index: Int) {
        val radius = body.radius
        val radiusX = radius * scaleX2
        val radiusY = radius * scaleY
        body.initialPosition.apply {
            vertices?.put(
                8 * index, floatArrayOf(
                    x - radiusX, y + radiusY, x - radiusX, y - radiusY,
                    x + radiusX, y + radiusY, x + radiusX, y - radiusY
                )
            )
        }
    }

    private fun drawFrame() {
        glClear(GL_COLOR_BUFFER_BIT)
        glUniform4f(glGetUniformLocation(programId, U_BACKGROUND), 1f, 1f, 1f, 0f)
        verticesBuffer?.passToShader(programId, A_POSITION)
        uvBuffer?.passToShader(programId, A_UV)
        circles.forEachIndexed { i, circle -> circle.drawItself(programId, i, scaleX2, scaleY) }
    }

    private fun enableTransparency() {
        glEnable(GLES20.GL_BLEND)
        glBlendFunc(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA)
        attachShaders()
    }

    private fun attachShaders() {
        programId = createProgram(
            createShader(GL_VERTEX_SHADER, vertexShader),
            createShader(GL_FRAGMENT_SHADER, fragmentShader)
        )
        glUseProgram(programId)
    }

    private fun createProgram(vertexShader: Int, fragmentShader: Int) = glCreateProgram().apply {
        glAttachShader(this, vertexShader)
        glAttachShader(this, fragmentShader)
        glLinkProgram(this)
    }

    private fun createShader(type: Int, shader: String) = GLES20.glCreateShader(type).apply {
        glShaderSource(this, shader)
        glCompileShader(this)
    }

    private fun clear() {
        circles.clear()
        Engine.clear()
    }
}