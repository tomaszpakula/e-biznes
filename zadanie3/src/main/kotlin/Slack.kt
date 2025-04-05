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

class Slack():Bot() {
    @Serializable
    data class SlackChannelListResponse(val ok: Boolean, val channels: List<SlackChannel>)

    @Serializable
    data class SlackChannel(val id: String, val name: String)

    @Serializable
    data class SlackMessageHistoryResponse(val ok: Boolean, val messages: List<SlackMessage> = emptyList())

    @Serializable
    data class SlackMessage(val text: String, val user: String? = null, val ts: String)

    private val json = Json { ignoreUnknownKeys = true }
    private val channelName = "test"
    private var lastReadMessageId : String ? = null
    override suspend fun createChannel(recipient_id: String) {
        channelId = getChannelIdByName("test")
        readMessages()
    }
    val SLACK_TOKEN: String = dotenv["SLACK_TOKEN"] ?: error("BOT_TOKEN not found in .env")
    private suspend fun getChannelIdByName(name: String): String? {
        val response: HttpResponse = client.get("https://slack.com/api/conversations.list") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $SLACK_TOKEN")
            }
        }

        println("channel add ${response.bodyAsText()}")
        if (response.status != HttpStatusCode.OK) {
            println("Failed to fetch channel list: ${response.status}")
            return null
        }

        val jsonBody = response.bodyAsText()
        val channels = json.decodeFromString<SlackChannelListResponse>(jsonBody)
        return channels.channels.find { it.name == name }?.id
    }

    override suspend fun sendMessage(message: MessageRequest) {
        if (channelId == null) return

        val response = client.post("https://slack.com/api/chat.postMessage") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $SLACK_TOKEN")
                append(HttpHeaders.ContentType, ContentType.Application.Json.toString())
            }
            setBody("""{
                "channel": "$channelId",
                "text": "${message.content}"
            }""")
        }

        println("Send response: ${response.bodyAsText()}")
        if (response.status != HttpStatusCode.OK) {
            println("Failed to send message: ${response.status}")
        }
        readMessages()
    }

    override suspend fun readMessages(): Message? {
        if (channelId == null) return null

        val url = buildString {
            append("https://slack.com/api/conversations.history")
            append("?channel=$channelId")
            append("&limit=1")
            if (lastReadMessageId != null) {
                append("&oldest=${lastReadMessageId}")
            }
        }

        val response = client.get(url) {
            headers {
                append(HttpHeaders.Authorization, "Bearer $SLACK_TOKEN")
            }
        }


        if (response.status != HttpStatusCode.OK) {
            println("Failed to read message: ${response.status}")
            return null
        }

        val jsonBody = response.bodyAsText()
        val history = json.decodeFromString<SlackMessageHistoryResponse>(jsonBody)
        if (!history.ok) {
            println("Slack API Error")
            return null
        }

        val message = history.messages.firstOrNull() ?: return null

        if (message.ts == lastReadMessageId) {
            return null
        }

        lastReadMessageId = message.ts

        return Message(
            id = message.ts,
            content = message.text,
            author = User(id = message.user ?: "unknown", username = "unknown")
        )
    }




}
