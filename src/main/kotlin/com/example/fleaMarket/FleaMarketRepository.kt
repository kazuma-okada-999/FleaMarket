package com.example.fleaMarket

import com.example.fleaMarket.Flema
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.sql.ResultSet

@Component
class FlemaRowMapper : RowMapper<Flema> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Flema {
        return Flema(rs.getLong("id"), rs.getString("item")?:"", rs.getString("description")?:"", rs.getBigDecimal("sell_price")?: BigDecimal.ZERO, rs.getInt("stock"), rs.getString("img_url")?:"")
    }
}

@Repository
class FleaMarketRepository(@Autowired val jdbcTemplate: JdbcTemplate, @Autowired val flemaRowMapper: FlemaRowMapper) {

    fun fetchItems(): Array<Flema> {
        val flema = jdbcTemplate.query("SELECT * FROM product", flemaRowMapper)
        println("Flema!!!!: ${flema}")
        return flema.toTypedArray()
    }

    fun saveFlema(FlemaRequest: FlemaRequest) {
        println("saveのリクエストです: ${FlemaRequest}");
        jdbcTemplate.update("INSERT INTO product (item, description, sell_price, stock, img_url) VALUES (?, ?, ?, ?, ?)",
            FlemaRequest.item,
            FlemaRequest.description,
            FlemaRequest.sellPrice,
            FlemaRequest.stock,
            FlemaRequest.imgUrl,
        )
    }
}