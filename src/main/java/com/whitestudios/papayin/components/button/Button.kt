package com.whitestudios.papayin.components.button

import android.annotation.TargetApi
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.os.Build
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.whitestudios.papayin.R
import com.whitestudios.papayin.R.styleable.*
import kotlinx.android.synthetic.main.button.view.*

class Button: LinearLayout {

    private val color: Int = ContextCompat.getColor(context, R.color.black)
    private val textColor: Int = ContextCompat.getColor(context, R.color.white)
    private val ripple: Int = ContextCompat.getColor(context, R.color.gray_300)
    private val minHeight = context.resources.getDimensionPixelSize(R.dimen.button_minHeight)
    private val borderRadius = context.resources.getDimension(R.dimen.button_border_default)

    var listener: Listener? = null

    constructor(context: Context): super(context) {
        inflate(context, R.layout.button, this)
        setupUI()
    }

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        inflate(context, R.layout.button, this)
        setupAttrs(context, attrs)
        setupUI()
    }

    fun setupAttrs(context: Context, attrs: AttributeSet){
        val typedArray = context.obtainStyledAttributes(attrs, custom_button_component, 0, 0)
        try {

            val buttonColor = typedArray.getColor(custom_button_component_button_background, color)
            setButtonBackground(buttonColor)

            val buttonTextColor = typedArray.getColor(custom_button_component_button_text_color, textColor)
            setTextActionColor(buttonTextColor)

        } finally {
            typedArray.recycle()
        }
    }

    fun setupUI() {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        minimumHeight = minHeight

        txt_action_button.setOnClickListener {
            listener?.onClick(it)
        }
    }

    fun setTextAction(text: String) {
        txt_action_button.text = text
    }

    fun setTextActionColor(txtColor: Int) {
        txt_action_button.setTextColor(txtColor)
    }

    fun setDrawableStart(drawableStart: Int) {
        txt_action_button.setCompoundDrawablesWithIntrinsicBounds(drawableStart, 0, 0, 0)
        txt_action_button.setPadding(32,0,32,0)
    }

    fun setButtonBackground(backgroundColor: Int, rippleColor: Int? = null) {
        val rpColor = rippleColor?: ripple

        val shape = GradientDrawable()
        shape.shape = GradientDrawable.RECTANGLE
        shape.cornerRadius = borderRadius
        shape.setColor(backgroundColor)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            txt_action_button.background = getBackgroundDrawable(rpColor, shape)
        } else {
            txt_action_button.background = shape
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getBackgroundDrawable(pressedColor: Int, backgroundDrawable: Drawable): RippleDrawable {
        return RippleDrawable(getPressedState(pressedColor), backgroundDrawable, null)
    }

    private fun getPressedState(pressedColor: Int): ColorStateList {
        return ColorStateList(arrayOf(intArrayOf()), intArrayOf(pressedColor))
    }

    interface Listener {
        fun onClick(view: View)
    }
}