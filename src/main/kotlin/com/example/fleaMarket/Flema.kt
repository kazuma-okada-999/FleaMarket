package com.example.fleaMarket

import java.math.BigDecimal

data class Flema(val id: Long, val item: String, val description: String, val sellPrice: BigDecimal, val stock: Int, val imgUrl: String)
