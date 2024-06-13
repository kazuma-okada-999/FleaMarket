package com.example.fleaMarket

import java.math.BigDecimal

data class FlemaRequest(val item: String, val description: String, val sellPrice: BigDecimal, val stock: Int, val imgUrl: String)