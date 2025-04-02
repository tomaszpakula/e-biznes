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
import io.github.cdimascio.dotenv.dotenv

val dotenv = Dotenv.load()

val BOT_TOKEN: String = dotenv["BOT_TOKEN"] ?: error("BOT_TOKEN not found in .env")
val USER_ID: String = dotenv["USER_ID"] ?: error("USER_ID not found in .env")
const val url = "https://discord.com/api/v10/"


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

suspend fun readMessages(){
    if(channelId == null){
        return
    }
    val url = buildString {
        append("https://discord.com/api/v10/channels/$channelId/messages?")
        if (lastReadMessageId != null) {
            append("after=$lastReadMessageId&")
        }
        append("limit=10")
    }


    val response: HttpResponse = client.get(url) {
        headers {
            append(HttpHeaders.Authorization, "Bot $BOT_TOKEN")
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
    }

    val responseBody = response.bodyAsText()
    val messages: List<DiscordMessage> = json.decodeFromString(responseBody)

    if (messages.isNotEmpty()) {
        lastReadMessageId = messages.first().id
    }

    for (message in messages) {
        println("${message.author.username}: ${message.content}")
    }
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

    println(sendMessageResponse.bodyAsText())
}

suspend fun createChannel(userId: String) {
    val response: DMChannelResponse = json.decodeFromString(client.post("$url/users/@me/channels"){
        headers {
            append(HttpHeaders.Authorization, "Bot $BOT_TOKEN")
            append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            append(HttpHeaders.UserAgent, "DiscordBot (https://discord.com, v10)")
        }
        setBody(Json.encodeToString(CreateDMRequest(recipient_id = userId)))
    }.body()
    )
    channelId = response.channelId
}

suspend fun main() {


    createChannel(USER_ID)

    runBlocking {
        while (true) {
            delay(1000)
            if(channelId != null){
                readMessages()
            }

            val message = readLine()
            if (!message.isNullOrEmpty()) {
                sendMessage(MessageRequest(message.trim()))
            }
        }
    }
}



