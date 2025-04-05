package com.example

import com.example.Bot.Message
import com.example.Bot.MessageRequest
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

private fun getCategories(): MessageRequest {
    val message = buildString {
        append("O to lista kategorii: \n")
        categories.forEach {
                category -> append("$category\n" )
        }
    }
    return MessageRequest(content=message)
}

private fun getProductsByCategory(category: String): MessageRequest {
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

private suspend fun handleProducts(message: Message, bot:Bot){
    if(message.content.startsWith("/Kategorie:")) {
        val category:String = message.content.removePrefix("/Kategorie:").trim()
        if (categories.contains(category)) {
            bot.sendMessage(getProductsByCategory(category))
        }
        else{
            bot.sendMessage(MessageRequest("Podana kategoria nie istnieje"))
            bot.sendMessage(getCategories())
        }
    }
    else{
        bot.sendMessage(MessageRequest("Nieprawidłowe polecenie. Upewnij się, że użyłeś jednej z poniższych komend: \n/Kategorie - lista kategorii\n/Kategorie:[nazwa kategorii] - produkty danej kategorii"))
    }
}


suspend fun main(){
    //val bot:Bot = Discord(BOT_TOKEN, USER_ID)
    val bot:Bot = Slack()
    bot.createChannel(USER_ID)
    runBlocking {
        while (true) {
            delay(1000)
            if(bot.channelId != null){
                val message: Message? = bot.readMessages()
                if (message != null) {
                    when (message.content) {
                        "/Kategorie" -> bot.sendMessage(getCategories())
                        else -> handleProducts(message, bot)
                    }
                }
            }
        }
    }
}



