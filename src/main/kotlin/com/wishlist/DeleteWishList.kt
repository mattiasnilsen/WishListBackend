package com.wishlist

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.wishlist.common.ApiGatewayRequest
import com.wishlist.common.ApiGatewayResponse
import com.wishlist.common.Database
import org.apache.logging.log4j.LogManager


@Suppress("unused")
class DeleteWishList : RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    override fun handleRequest(input: ApiGatewayRequest, context: Context): ApiGatewayResponse {
        val userID = input.requestContext?.authorizer?.claims?.sub.orEmpty()
        val parsedBody = Body.parse(input.body.orEmpty())

        if(parsedBody.id != null) {
            Database().deleteWishList(parsedBody.id, userID)
        }

        return ApiGatewayResponse.build {
            statusCode = 200
        }
    }

    data class Body(val id: Int? = null) {
        companion object {
            fun parse(str: String): Body {
                return ObjectMapper().readValue(str)
            }
        }
    }

    companion object {
        private val LOG = LogManager.getLogger(GetWishLists::class.java)
    }
}
