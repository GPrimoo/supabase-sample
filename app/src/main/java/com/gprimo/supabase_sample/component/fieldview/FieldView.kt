package com.gprimo.supabase_sample.component.fieldview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.gprimo.supabase_sample.R
import com.gprimo.supabase_sample.databinding.FieldViewBinding

class FieldView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: FieldViewBinding by lazy {
        FieldViewBinding.inflate(
            LayoutInflater.from(context), this, true
        )
    }

    init {
        attrs?.let { setupView(it) }
    }

    private fun setupView(attrs: AttributeSet) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.FieldView)

        val label: String? = attributes.getString(R.styleable.FieldView_field_view_label)
        val value: String? = attributes.getString(R.styleable.FieldView_field_view_value)

        val data = FieldViewData(
            label = label,
            value = value
        )

        setup(data)
    }

    fun setup(data: FieldViewData) {
        data.label?.let { setLabel(it) }
        data.value?.let { setValue(it) }
    }

    private fun setLabel(label: String) {
        binding.tvLabel.text = label
    }

    private fun setValue(value: String) {
        binding.tvValue.text = value
    }
}