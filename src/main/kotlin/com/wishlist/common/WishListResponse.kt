package com.wishlist.common


class WishListResponseObject(wishList: WishList)  {

    val name: String = wishList.name
    val owner: String = wishList.owner.name
}

data class WishListResponse(val WishLists: List<WishListResponseObject>) : Response()
