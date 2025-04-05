package com.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class Bot {
    @Serializable
    data class CreateDMRequest(val recipient_id: String)

    @Serializable
    data class DMChannelResponse(
        @SerialName("id") val channelId: String
    )

    @Serializable
    data class MessageRequest(val content: String)

    @Serializable
    data class Message(
        val id: String,
        val content: String,
        val author: User
    )

    @Serializable
    data class User(
        val id: String,
        val username: String
    )
    val products = mapOf(
        "Telefon" to "Technologia",
        "Komputer" to "Technologia",
        "ser" to "Produkty Spożywcze",
        "mleko" to "Produkty Spożywcze"
    )

    val categories = products.values.distinct()
    val client = HttpClient(CIO)
    var channelId:String? = null
    abstract suspend fun createChannel(recipient_id: String)
    abstract suspend fun readMessages(): Message?
    abstract suspend fun sendMessage(message: MessageRequest)
}
