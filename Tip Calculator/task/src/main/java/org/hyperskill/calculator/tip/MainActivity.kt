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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText.addTextChangedListener(object:TextWatcher {
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val tipPercent = tipPercentSeekBar.progress
                val amountSt = p0.toString()
                if (amountSt.isEmpty()) {
                    textBillAmount.text = null
                    tipPercentTv.text = null
                    return
                }
                val amount = amountSt.toDouble()
                if (amount > 0) {
                    textBillAmount.text = String.format("Bill Value: $%.2f", amount)
                    tipPercentTv.text = String.format("Tip: %d%%", tipPercent)
                } else {
                    textBillAmount.text = null
                    tipPercentTv.text = null
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
                    return
                }
                val amountSt = editText.text.toString()
                if (amountSt.isEmpty()) {
                    tipPercentTv.text = null
                    return
                }
                val amount = amountSt.toDouble()
                if (amount > 0) {
                    tipPercentTv.text = String.format("Tip: %d%%", progress)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
    }
}