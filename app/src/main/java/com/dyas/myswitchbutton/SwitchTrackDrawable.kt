package com.dyas.myswitchbutton

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes

class SwitchTrackDrawable(
    context: Context,
    @StringRes leftTextId: Int,
    @StringRes rightTextId: Int
): Drawable() {
    private var mContext: Context
    private var mLeftText: String
    private var mRightText: String
    private var mTextPaint: Paint

    init {
        mContext = context

        // Left text
        mLeftText = context.getString(leftTextId)

        // Left text
        mTextPaint = createTextPaint()

        // Right text
        mRightText = context.getString(rightTextId)
    }

    private fun createTextPaint(): Paint {
        val textPaint = Paint()
        textPaint.color = mContext.resources.getColor(R.color.darker_gray)
        textPaint.isAntiAlias = true
        textPaint.style = Paint.Style.FILL
        textPaint.textAlign = Paint.Align.CENTER
        textPaint.textSize = 24f
        // Set textSize, typeface, etc, as you wish
        return textPaint
    }

    @SuppressLint("ResourceAsColor")
    override fun draw(canvas: Canvas) {
        val textBounds = Rect()
        mTextPaint.getTextBounds(mRightText, 0, mRightText.length, textBounds)

        // The baseline for the text: centered, including the height of the text itself
        val heightBaseline: Int = canvas.clipBounds.height() / 2 + textBounds.height() / 2

        // This is one quarter of the full width, to measure the centers of the texts
        val widthQuarter: Int = canvas.clipBounds.width() / 4
        canvas.drawText(
            mLeftText, 0, mLeftText.length,
            widthQuarter.toFloat(), heightBaseline.toFloat(),
            mTextPaint
        )
        canvas.drawText(
            mRightText, 0, mRightText.length,
            (widthQuarter * 3).toFloat(), heightBaseline.toFloat(),
            mTextPaint
        )
        canvas.drawColor(R.color.white)

    }

    override fun setAlpha(p0: Int) {
    }

    override fun setColorFilter(p0: ColorFilter?) {
    }

    override fun getOpacity(): Int = PixelFormat.UNKNOWN
}