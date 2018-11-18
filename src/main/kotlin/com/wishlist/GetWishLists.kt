package com.wishlist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.wishlist.common.*
import org.apache.logging.log4j.LogManager

@Suppress("unused")
class GetWishLists: RequestHandler<ApigatewayRequest.Input, ApiGatewayResponse> {


    override fun handleRequest(input: ApigatewayRequest.Input, context: Context): ApiGatewayResponse {
        val db = Database()
        val accountId = input.requestContext?.accountId.orEmpty()
        val serializedWishLists = db.retrieveMyWishLists(accountId).map(WishList::serialize)


        return ApiGatewayResponse.build {
            statusCode = 200
            objectBody = WishListResponse(serializedWishLists)
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetWishLists::class.java)
    }
}
