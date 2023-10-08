package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val editText: TextView by lazy { findViewById(R.id.edit_text) }
    val billAmountTv: TextView by lazy { findViewById(R.id.bill_value_tv) }
    val tipPercentTv: TextView by lazy { findViewById(R.id.tip_percent_tv) }
    val tipPercentSeekBar: SeekBar by lazy { findViewById(R.id.seek_bar) }
    val tipAmountTv: TextView by lazy { findViewById(R.id.tip_amount_tv) }

    private val tipPercent: Int
        get() = tipPercentSeekBar.progress

    val billAmount: Double?
        get() = editText.text.toString().toDoubleOrNull()

    private fun clearTextViews() {
        billAmountTv.text = null
        tipPercentTv.text = null
        tipAmountTv.text = null
    }

    private fun updateNumbersInTextViews() {
        billAmountTv.text = String.format(getString(R.string.bill_value_format_string), billAmount)
        tipPercentTv.text = String.format(getString(R.string.tip_percentage_format_string), tipPercent)
        tipAmountTv.text = String.format(getString(R.string.tip_amount_format_string), billAmount!! * tipPercent / 100)
    }
    private fun billAmountIsPositive(): Boolean {
        return billAmount != null && billAmount!! > 0
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.addTextChangedListener(object:TextWatcher {
            override fun onTextChanged(seq: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (billAmountIsPositive()) updateNumbersInTextViews() else clearTextViews()
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
        })

        tipPercentSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                if (billAmountIsPositive()) updateNumbersInTextViews()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
}