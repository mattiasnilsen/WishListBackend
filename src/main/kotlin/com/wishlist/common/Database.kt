package com.wishlist.common

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

class Database {

    init {
        Database.connect(
            url = System.getenv("WISHLIST_DB_URL") ?: "",
            driver = "org.postgresql.Driver",
            user = System.getenv("WISHLIST_DB_USER") ?: "",
            password = System.getenv("WISHLIST_DB_PASS") ?: "")
    }

    fun retrieveAllWishLists() : List<WishList> {
        return transaction {
            WishList.all().toList()
        }
    }

}
