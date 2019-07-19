package com.wishlist.common

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntIdTable


object Wishes : IntIdTable() {
    val wish = varchar("wish", 500)
    val wishList = reference("wishlist", WishLists)
}

class Wish(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, Wish>(Wishes)

    var wish  by Wishes.wish
    var wishList by WishList referencedOn Wishes.wishList
}
