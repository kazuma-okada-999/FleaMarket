package com.example.fleaMarket

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@Sql("/001-product.sql")
class FleaMarketApplicationTests(
	@Autowired val restTemplate: TestRestTemplate,
	@LocalServerPort val port: Int
) {


	@Test
	fun `最初のテスト`(){
		assertThat(1+2, equalTo(3))
	}

	@Test
	fun `GETリクエストはOKステータスを返す`(){
		// localhost/items に GETリクエストを発行する。
		val response = restTemplate.getForEntity("http://localhost:$port/api/items", String::class.java)
		// レスポンスのステータスコードは OK である。
		println("response: ${response}")
		assertThat(response.statusCode, equalTo(HttpStatus.OK))
	}

	@Test
	fun `GETリクエストはTodoオブジェクトのリストを返す`(){
		// localhost/todos に GETリクエストを送り、レスポンスを Todoオブジェクトの配列として解釈する。
		val response = restTemplate.getForEntity("http://localhost:$port/api/items", Array<Flema>::class.java)

		assertThat(response.statusCode, equalTo(HttpStatus.OK))

// レスポンスの Content-Type は application/json であること。
		assertThat(response.headers.contentType, equalTo(MediaType.APPLICATION_JSON))
		// 配列は2つの要素をもつこと。
		val items = response.body!!

		println("Response!!!!: ${items[0]}")


		assertThat(items.size, equalTo(2))
//		// 最初の要素は id=1 であり、text が "foo" であること。
//		assertThat(todos[0].id, equalTo(1))
//		assertThat(todos[0].text, equalTo("foo"))
//		// 次の要素は id=2 であり、text が "bar" であること。
//		assertThat(todos[1].id, equalTo(2))
//		assertThat(todos[1].text, equalTo("bar"))

	}
}
