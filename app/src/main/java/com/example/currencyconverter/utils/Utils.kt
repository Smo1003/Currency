package com.example.currencyconverter.utils

class Utils {
    companion object {
        fun cleanMoney(money: String) : String {
            var result = money

            result = result.replace(" ", "")
            result = result.replace(",", ".")

            return result
        }

        fun moneyFormatter(money: String) : String {
            var origin = money

            var result: String = ""

            var commaIdx : Int = origin.indexOf('.')
            if (commaIdx == -1) {
                commaIdx = origin.length
            } else  {
                result += ","
            }

            for (i in commaIdx + 1..<origin.length) {
                result += money[i]
                if ((i - commaIdx) % 3 == 0) result += " "
            }
            for (i in commaIdx - 1 downTo 0) {
                result = money[i] + result
                if ((commaIdx - i) % 3 == 0 && i > 0) result = " $result"
            }

            return result
        }
    }
}