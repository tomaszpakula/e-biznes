package com.example

import io.github.cdimascio.dotenv.Dotenv
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.SerialName

val dotenv = Dotenv.load()

val BOT_TOKEN: String = dotenv["BOT_TOKEN"] ?: error("BOT_TOKEN not found in .env")
val USER_ID: String = dotenv["USER_ID"] ?: error("USER_ID not found in .env")
const val url = "https://discord.com/api/v10/"

val products = mapOf("Telefon" to "Technologia", "Komputer" to "Technologia", "ser" to "Produkty Spożywcze" ,"mleko" to "Produkty Spożywcze" )
val categories = products.values.distinct()

@Serializable
data class CreateDMRequest(val recipient_id: String)

@Serializable
data class DMChannelResponse(
    @SerialName("id") val channelId: String
)

@Serializable
data class MessageRequest(val content: String)

@Serializable
data class DiscordMessage(
    val id: String,
    val content: String,
    val author: DiscordUser
)

@Serializable
data class DiscordUser(
    val id: String,
    val username: String
)

private val json = Json {
    ignoreUnknownKeys = true
}
val client = HttpClient(CIO)
var lastReadMessageId : String ? = null
var channelId: String ? = null

suspend fun readMessages(): DiscordMessage? {
    if(channelId == null){
        return null
    }
    val url = buildString {
        append("https://discord.com/api/v10/channels/$channelId/messages?")
        if (lastReadMessageId != null) {
            append("after=$lastReadMessageId&")
        }
        append("limit=1")
    }


    val response: HttpResponse = client.get(url) {
        headers {
            append(HttpHeaders.Authorization, "Bot $BOT_TOKEN")
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
    }

    if(response.status != HttpStatusCode.OK) {
        println(response.status)
    }

    val responseBody = response.bodyAsText()
    val messages: List<DiscordMessage> = json.decodeFromString(responseBody)
    if (messages.isNotEmpty() ) {
        lastReadMessageId = messages.first().id
        if(messages[0].author.id != USER_ID){
            return null
        }
    }
    else {
        return null
    }

    return messages[0]
}

suspend fun sendMessage(message: MessageRequest){
    if (channelId == null){
        return
    }
    val sendMessageResponse: HttpResponse = client.post("https://discord.com/api/v10/channels/${channelId}/messages") {
        headers {
            append(HttpHeaders.Authorization, "Bot $BOT_TOKEN")
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
        setBody(Json.encodeToString(message))
    }

    if(sendMessageResponse.status != HttpStatusCode.OK) {
        println(sendMessageResponse.status)
    }

}

suspend fun createChannel(userId: String) {
    val response: HttpResponse = client.post("$url/users/@me/channels"){
        headers {
            append(HttpHeaders.Authorization, "Bot $BOT_TOKEN")
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            append(HttpHeaders.UserAgent, "DiscordBot (https://discord.com, v10)")
        }
        setBody(Json.encodeToString(CreateDMRequest(recipient_id = userId)))
    }

    if(response.status != HttpStatusCode.OK) {
        println(response.bodyAsText())
        return
    }

    val responseBody: DMChannelResponse = json.decodeFromString(response.body())

    channelId = responseBody.channelId
    //Wczytaj poprzednią najnowszą wiadomość, aby nie brać jej już pod uwagę
    readMessages()

}

fun getCategories():MessageRequest{
    val message = buildString {
        append("O to lista kategorii: \n")
        categories.forEach {
            category -> append("$category\n" )
        }
    }
    return MessageRequest(content=message)
}

fun getProductsByCategory(category: String):MessageRequest{
    val filteredProducts = products.filter {
            product -> product.value == category
    }
    val message = buildString {
        append("O to lista produktów: \n")
        filteredProducts.forEach {
            product -> append("${product.key}\n")
        }
    }
    return MessageRequest(content=message)
}

suspend fun handleProducts(message:DiscordMessage){
    if(message.content.startsWith("/Kategorie:")) {
        val category:String = message.content.removePrefix("/Kategorie:").trim()
        if (categories.contains(category)) {
            sendMessage(getProductsByCategory(category))
        }
        else{
            sendMessage(MessageRequest("Podana kategoria nie istnieje"))
            sendMessage(getCategories())
        }
    }
    else{
        sendMessage(MessageRequest("Nieprawidłowe polecenie. Upewnij się, że użyłeś jednej z poniższych komend: \n/Kategorie - lista kategorii\n/Kategorie:[nazwa kategorii] - produkty danej kategorii"))
    }
}

suspend fun main() {
    createChannel(USER_ID)
    runBlocking {
        while (true) {
            delay(1000)
            if(channelId != null){
                val message: DiscordMessage? = readMessages()
                if (message != null) {
                    when (message.content) {
                        "/Kategorie" -> sendMessage(getCategories())
                        else -> handleProducts(message)
                    }
                }
            }
        }
    }
}



