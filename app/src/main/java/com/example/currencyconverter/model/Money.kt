package com.example.currencyconverter.model

class Money(
    private val country: String,
    private val unit: String,
    private val weight: Double
) {
    fun getCountry() : String  = country
    fun getUnit() : String = unit
    fun getWeight() : Double = weight

    override fun toString(): String {
        return "$country - $unit"
    }
}