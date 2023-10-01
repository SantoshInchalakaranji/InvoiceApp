package com.prplmnstr.invoiceapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class SignatureView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint: Paint = Paint()
    private val path = android.graphics.Path()

    init {
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        paint.strokeWidth = 6f
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path.moveTo(x, y)
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
            }
            MotionEvent.ACTION_UP -> {
                // Touch up event, save the signature as an image
                saveSignatureAsImage()
            }
            else -> return false
        }

        // Force a redraw
        invalidate()
        return true
    }

    private fun saveSignatureAsImage() {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        draw(canvas)

        // Save the bitmap as an image file
        val file = File(context.externalCacheDir, "signature.png")
        try {
            val stream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.flush()
            stream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // Now you can use the image file (file) in your application
    }
}
