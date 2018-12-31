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

    fun saveWishList(newName: String, newOwner: String) : WishList {
        return transaction {
            WishList.new {
                name = newName
                owner = newOwner
            }
        }
    }

    fun deleteWishList(id: Int) {
        transaction {
            WishList.findById(id)?.delete()
        }
    }

    fun retrieveMyWishLists(accountId: String): List<WishList> {
        return transaction {
            WishList.find{WishLists.accountID eq accountId}.toList()
        }
    }

    fun createUser(userName: String, newAccountID: String) : User {
        return transaction {
            User.new(newAccountID) {
                name = userName
            }
        }
    }
}
