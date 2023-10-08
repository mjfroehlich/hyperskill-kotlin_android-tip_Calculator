package org.hyperskill.calculator.tip

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val editText: TextView by lazy { findViewById(R.id.edit_text) }
    val textBillAmount: TextView by lazy { findViewById(R.id.bill_value_tv) }
    val tipPercentTv: TextView by lazy { findViewById(R.id.tip_percent_tv) }
    val tipPercentSeekBar: SeekBar by lazy { findViewById(R.id.seek_bar) }
    val tipAmountTv: TextView by lazy { findViewById(R.id.tip_amount_tv) }

    private fun clearTextViews() {
        textBillAmount.text = null
        tipPercentTv.text = null
        tipAmountTv.text = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.addTextChangedListener(object:TextWatcher {
            override fun onTextChanged(seq: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val tipPercent = tipPercentSeekBar.progress
                val amountSt = seq.toString()
                if (amountSt.isEmpty()) {
                    clearTextViews()
                    return
                }
                val amount = amountSt.toDouble()
                if (amount > 0) {
                    textBillAmount.text = String.format(getString(R.string.bill_value_format_string), amount)
                    tipPercentTv.text = String.format(getString(R.string.tip_percentage_format_string), tipPercent)
                    tipAmountTv.text = String.format(getString(R.string.tip_amount_format_string), amount * tipPercent / 100)
                } else {
                    clearTextViews()
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // do nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                // do nothing
            }

        })

        tipPercentSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
                if (editText.text.isEmpty()) {
                    tipPercentTv.text = null
                    tipAmountTv.text = null
                    return
                }
                val billValueText = editText.text.toString()
                if (billValueText.isEmpty()) {
                    tipPercentTv.text = null
                    tipAmountTv.text = null
                    return
                }
                val amount = billValueText.toDouble()
                val tipPercent = progress
                if (amount > 0) {
                    tipPercentTv.text = String.format(getString(R.string.tip_percentage_format_string), tipPercent)
                    tipAmountTv.text = String.format(getString(R.string.tip_amount_format_string), amount * tipPercent / 100)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
}