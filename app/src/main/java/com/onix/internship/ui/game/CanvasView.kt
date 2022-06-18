package com.onix.internship.ui.game

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.onix.internship.R
import kotlin.math.floor
import kotlin.random.Random
import kotlin.random.nextInt

private enum class StatusCell { EMPTY, X, O }

class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var canvasFirstStep = ""
    var canvasUserChip = ""


    private lateinit var enumUser: StatusCell
    private lateinit var enumDevice: StatusCell

    data class cell(val i: Int, val j: Int)
    var cellMutableList = mutableListOf<cell>()

    private val gameArray: Array<Array<StatusCell>> = Array(3) { Array(3) { StatusCell.EMPTY } }

    private val clipRectTop = resources.getDimension(R.dimen.clipRectTop)
    private val clipRectLeft = resources.getDimension(R.dimen.clipRectLeft)
    private var motionTouchEventX = clipRectLeft
    private var motionTouchEventY = clipRectTop

    private val paint = Paint().apply {
        // Smooth out edges of what is drawn without affecting shape.
        isAntiAlias = true
        // Dithering affects how colors with higher-precision than the device are down-sampled.
        isDither = true
        style = Paint.Style.STROKE // default: FILL
        strokeJoin = Paint.Join.ROUND // default: MITER
        strokeCap = Paint.Cap.ROUND // default: BUTT
        strokeWidth =
            resources.getDimension(R.dimen.strokeWidth) // default: Hairline-width (really thin)
    }

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val path = Path()

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)

        if (canvasUserChip == "X") {
            enumUser = StatusCell.X
            enumDevice = StatusCell.O
        } else {
            enumUser = StatusCell.O
            enumDevice = StatusCell.X
        }
        for (i in 0 until gameArray.size){
            for (j in 0 until gameArray[i].size){
                cellMutableList.add(cell(i,j))
            }
        }
        if (canvasFirstStep == "Device") deviceStep()

        cellMutableList
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap, clipRectLeft, clipRectTop, null)
        path.reset()
        path.moveTo((width / 3).toFloat(), clipRectTop)
        path.lineTo((width / 3).toFloat(), height.toFloat())
        path.moveTo((width / 3).toFloat() * 2, clipRectTop)
        path.lineTo((width / 3).toFloat() * 2, height.toFloat())
        path.moveTo(clipRectLeft, (height / 3).toFloat())
        path.lineTo(width.toFloat(), (height / 3).toFloat())
        path.moveTo(clipRectLeft, (height / 3).toFloat() * 2)
        path.lineTo(width.toFloat(), (height / 3).toFloat() * 2)
        extraCanvas.drawPath(path, paint)
        invalidate()

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchClick()
        }
        return true
    }

    private fun touchClick() {
        getCell(enumUser)
        deviceStep()
        if (cellMutableList.size == 0) {
            Snackbar.make(this, "Game over", Snackbar.LENGTH_SHORT).show()
            return
        }
    }

    private fun getCell(enumStatus: StatusCell) {
        val i = floor(motionTouchEventY / (height / 3)).toInt()
        val j = floor(motionTouchEventX / (width / 3)).toInt()

        if (gameArray[i][j] != StatusCell.EMPTY) {
            return
        }
        gameArray[i][j] = enumStatus
        cellMutableList.remove(cell(i,j))

        drawCell(enumStatus, i, j)
    }

    private fun deviceStep(){
        if (cellMutableList.size == 0) {
            Snackbar.make(this, "Game over", Snackbar.LENGTH_SHORT).show()
            return
        }
        val nextCellIndex = Random.nextInt(0 until cellMutableList.size)
        val cell = cellMutableList[nextCellIndex]
        motionTouchEventY = (cell.i * (width/ 3) + width/ 3 / 2).toFloat()
        motionTouchEventX = (cell.j * (height / 3) + width/ 3 / 2).toFloat()
        getCell(enumDevice)
    }

    private fun drawCell(cell: StatusCell, i: Int, j: Int) {
        if (cell == StatusCell.X) {
            drawX(i, j)
        } else {
            drawO(i, j)
        }
    }

    private fun drawX(i: Int, j: Int) {
        var startX = (j * width / 3).toFloat()
        val startY = (i * height / 3).toFloat()
        path.reset()
        path.moveTo(startX + 50, startY + 50)
        path.lineTo(startX + width / 3 - 50, startY + height / 3 - 50)
        startX += width / 3
        path.moveTo(startX - 50, startY + 50)
        path.lineTo(startX - width / 3 + 50, startY + height / 3 - 50)
        extraCanvas.drawPath(path, paint)
        invalidate()
    }

    private fun drawO(i: Int, j: Int) {
        path.reset()
        val centerX = (j * width / 3).toFloat() + (width/3/2)
        val centerY = (i * height / 3).toFloat() + (height/3/2)
        path.addCircle(centerX, centerY, (width/3/2).toFloat() - 50, Path.Direction.CW)
        extraCanvas.drawPath(path, paint)
        invalidate()
    }
}