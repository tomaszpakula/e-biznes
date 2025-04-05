package com.example

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

class Discord(private val BOT_TOKEN: String, private val USER_ID: String) : Bot() {


    private val json = Json {
        ignoreUnknownKeys = true
    }

    private var lastReadMessageId : String ? = null

    override suspend fun readMessages(): Message? {
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
        val messages: List<Message> = json.decodeFromString(responseBody)
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

    override suspend fun sendMessage(message: MessageRequest){
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

    override suspend fun createChannel(userId: String) {
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


}