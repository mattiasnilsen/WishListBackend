package com.wishlist.common


class WishListResponseObject(wishList: WishList)  {

    val name: String = wishList.name
    val owner: String = wishList.owner.name
    val id: Int = wishList.id.value
}

data class WishListResponse(val WishLists: List<WishListResponseObject>) : Response()
