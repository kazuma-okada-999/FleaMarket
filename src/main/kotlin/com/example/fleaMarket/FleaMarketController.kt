package com.example.fleaMarket


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.util.UriComponentsBuilder
import java.awt.PageAttributes
import java.net.URI
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import java.util.*


var HttpHeaders.contentType: MediaType?
    get() = this.contentType
    set(value) {
        value?.let {
            this.contentType = it
        }
    }

@RestController
class FleaMarketRestController(@Autowired val fleaMarketRepository: FleaMarketRepository) {

    @GetMapping("/api/items")
    fun getItems(): Array<Flema> {
        val res = fleaMarketRepository.fetchItems()
        println("res!!!!!!!!!!!: ${res}")
        return res
    }

    @PostMapping("/api/items/submit")
    fun saveItems(@RequestBody flemaRequest: FlemaRequest):  ResponseEntity<String>{
        println("リクエストです: ${flemaRequest}");
        val res = fleaMarketRepository.saveFlema(flemaRequest)
        return ResponseEntity(HttpStatus.CREATED)
    }

    @Value("\${imgbb.api.key}")
    private lateinit var imgBbApiKey: String

    @PostMapping("/api/upload")
    fun uploadImage(@RequestParam("image") imageFile: MultipartFile): ResponseEntity<String> {
        println("アップロード: ");
        if (imageFile.isEmpty) {
            return ResponseEntity.badRequest().body("画像を選択してください")
        }

        // ImgBBにアップロードするためのAPIリクエストを作成
        val apiUrl = "https://api.imgbb.com/1/upload?key=$imgBbApiKey"
        val restTemplate = RestTemplate()

        val imageBytes = imageFile.bytes
        val encodedImage = Base64.getEncoder().encodeToString(imageBytes)
//        println("64: ${encodedImage}")
        val body = LinkedMultiValueMap<String, Any>()
        body.add("key", imgBbApiKey)
        body.add("image", encodedImage)

        val headers = HttpHeaders()
        headers.contentType = MediaType.MULTIPART_FORM_DATA

        val requestEntity = HttpEntity(body, headers)
        val response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String::class.java)

        return if (response.statusCode.is2xxSuccessful) {
            // 正常のときはImgBBからのレスポンスをそのまま返す
            ResponseEntity.ok(response.body)
        } else {
            ResponseEntity.status(response.statusCode).body("エラー")
        }
    }


}