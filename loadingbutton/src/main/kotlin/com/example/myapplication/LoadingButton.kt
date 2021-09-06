package com.example.myapplication

import android.content.Context
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar

enum class LoadingButtonState {
    LOADING,
    COMPLETE,
}

class LoadingButton @JvmOverloads constructor (context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private var state: LoadingButtonState = LoadingButtonState.COMPLETE
    private val button: AppCompatButton
    private val progress: ProgressBar
    private val text: AppCompatTextView

    init {
        inflate(context, R.layout.loading_button_view, this)
        button = findViewById(R.id.loading_button_button)
        progress = findViewById(R.id.loading_button_progress)
        text = findViewById(R.id.loading_button_text)

        val ta = context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingButton, 0, 0)
        text.text = ta.getText(R.styleable.LoadingButton_text)
    }

    fun updateState(new: LoadingButtonState) {
        state = new
        when (new) {
            LoadingButtonState.COMPLETE -> {
                toComplete()
            }
            LoadingButtonState.LOADING -> {
                toLoading()
            }
        }
    }

    fun setClickListener(clickListener: OnClickListener) {
        return button.setOnClickListener(clickListener)
    }

    fun setText(text: String) {
        this.text.text = text
    }

    fun updateEnabled(isEnabled: Boolean) {
        button.isClickable = isEnabled
        button.isEnabled = isEnabled
        text.isEnabled = isEnabled
    }

    fun getState(): LoadingButtonState = state

    private fun toLoading() {
        button.isClickable = false
        button.isEnabled = false
        text.isEnabled = false
        TransitionManager.beginDelayedTransition(this, ChangeBounds())
        val lp = button.layoutParams
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT
        button.layoutParams = lp
        button.minWidth = button.height
        progress.animate().alpha(1f)
        text.animate().setDuration(100).alpha(0f)
    }

    private fun toComplete() {
        button.isClickable = true
        button.isEnabled = true
        text.isEnabled = true
        TransitionManager.beginDelayedTransition(this, ChangeBounds())
        val lp = button.layoutParams
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT
        button.layoutParams = lp
        progress.animate().setDuration(100).alpha(0f)
        text.animate().alpha(1f)
    }
}