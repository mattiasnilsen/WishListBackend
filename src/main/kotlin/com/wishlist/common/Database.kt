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

    fun saveWishList(newOwner: String, newName: String) {
        transaction {
            val user = getUser(newOwner)
            if(user != null) {
                WishList.new {
                    owner = user
                    name = newName
                }
            }
        }
    }

    fun deleteWishList(id: Int, owner: String) {
        transaction {
            val wishListToDelete = WishList.findById(id)
            if(wishListToDelete?.owner?.id?.value == owner) {
                wishListToDelete.delete()
            }
        }
    }

    fun retrieveMyWishLists(owner: String): List<WishList> {
        return transaction {
            WishList.find{WishLists.owner eq owner}.toList()
        }
    }

    fun createUser(userName: String, newAccountID: String) : User {
        return transaction {
            User.new(newAccountID) {
                name = userName
            }
        }
    }

    private fun getUser(id: String) : User? {
        return transaction {
            User.findById(id)
        }
    }
}
