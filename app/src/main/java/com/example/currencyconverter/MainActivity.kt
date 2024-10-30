package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.currencyconverter.model.Money
import com.example.currencyconverter.utils.Utils

class MainActivity : ComponentActivity() {
    private lateinit var dstCurrency: TextView
    private lateinit var dstMoney: EditText
    private lateinit var spnDstMoney: Spinner

    private lateinit var spnSrcMoney: Spinner
    private lateinit var srcCurrency: TextView
    private lateinit var srcMoney: EditText

    private var srcWeight: Double = 1.0
    private var dstWeight: Double = 1.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        dstCurrency = findViewById(R.id.dstCurrency)
        spnDstMoney = findViewById(R.id.spnDstMoney)
        dstMoney = findViewById(R.id.dstMoney)

        spnSrcMoney = findViewById(R.id.spnSrcMoney)
        srcCurrency = findViewById(R.id.srcCurrency)
        srcMoney = findViewById(R.id.srcMoney)

        srcMoney.setText((0).toString())
        srcMoney.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (!p0.isNullOrEmpty()) {
                    srcMoney.removeTextChangedListener(this)

                    val result = Utils.moneyFormatter(Utils.cleanMoney(p0.toString()))
                    srcMoney.setText(result)
                    srcMoney.setSelection(result.length)

                    srcMoney.addTextChangedListener(this)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                converting()
            }
        })

        val srcData = getData()
        val spnSrcAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, srcData)
        spnSrcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnSrcMoney.adapter = spnSrcAdapter
        spnSrcMoney.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                updateSrcMoney(srcData[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spnSrcMoney.setSelection(0, true)

        val dstData = getData()
        val spnDstAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dstData)
        spnDstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnDstMoney.adapter = spnDstAdapter
        spnDstMoney.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                updateDstMoney(dstData[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        spnDstMoney.setSelection(1, true)
    }

    private fun updateSrcMoney(money: Money) {
        srcCurrency.text = money.getUnit()
        srcWeight = money.getWeight()
        converting()
    }

    private fun updateDstMoney(money: Money) {
        dstCurrency.text = money.getUnit()
        dstWeight = money.getWeight()
        converting()
    }
    private fun converting() {
        val result: Double? = Utils.cleanMoney(srcMoney.text.toString()).toDoubleOrNull();
        if (result != null) {
            dstMoney.setText(Utils.moneyFormatter((result * dstWeight / srcWeight).toString()))
        } else {
            dstMoney.setText("0")
        }
    }

    private fun getData() : List<Money> = listOf(
        Money("Hoa Kỳ", "USD", 1.0),
        Money("Việt Nam", "VND", 25280.0),
        Money("Liên minh châu Âu", "EUR", 0.9236),
        Money("Nhật Bản", "JPY", 153.04),
        Money("Anh", "GBP", 0.7709),
        Money("Trung Quốc", "CNY", 7.1222),
        Money("Úc", "AUD", 1.52),
        Money("Canada", "CAD", 1.3925),
        Money("Thuỵ Sĩ", "CHF", 0.8676),
        Money("Hồng Kông", "HKD", 7.7712),
        Money("Singapore", "SGD", 1.3233),
        Money("Thuỵ Điển", "SEK", 10.6711),
        Money("Hàn Quốc", "KRW", 1379.08),
        Money("Na Uy", "NOK", 10.9592),
        Money("New Zealand", "NZD", 1.6703),
        Money("Ấn Độ", "INR", 84.062),
        Money("Mexico", "MXN", 20.1362),
        Money("Đài Loan", "TWD", 31.971),
        Money("Nam Phi", "ZAR", 17.6298),
        Money("Brazil", "BRL", 5.7696),
        Money("Đan Mạch", "DKK", 6.8907),
        Money("Ba Lan", "PLN", 4.0093),
        Money("Thái Lan", "THB", 33.75),
        Money("Israel", "ILS", 3.7098),
        Money("Indonesia", "IDR", 15690.0),
        Money("Cộng hoà Séc", "CZK", 23.441),
        Money("UAE", "AED", 3.6729),
        Money("Thổ Nhĩ Kỳ", "TRY", 34.2895),
        Money("Hungary", "HUF", 376.95),
        Money("Chile", "CLP", 958.48),
        Money("Ả Rập Xê Út", "SAR", 3.7554),
        Money("Philippine", "PHP", 58.213),
        Money("Malaysia", "MYR", 4.375),
        Money("Colombia", "COP", 4393.32),
        Money("Nga", "RUB", 96.9),
        Money("Romania", "RON", 4.5929),
        Money("Peru", "PEN", 3.77),
        Money("Bahrain", "BHD", 0.377),
        Money("Bulgaria", "BGN", 1.8063),
        Money("Argentina", "ARS", 987.5)
    )
}