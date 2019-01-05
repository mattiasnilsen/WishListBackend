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
class CreateWishList : RequestHandler<ApiGatewayRequest, ApiGatewayResponse> {
    override fun handleRequest(input: ApiGatewayRequest, context: Context): ApiGatewayResponse {
        var statusCode = 200
        val parsedBody = Body.parse(input.body.orEmpty())
        val name = parsedBody.name.orEmpty()
        val userID = input.requestContext?.authorizer?.claims?.sub.orEmpty()

        if(name.isEmpty()) {
           LOG.error("Cannot create wish list with empty name")
           statusCode = 400
        } else {
            Database().saveWishList(userID, name)
        }

        return ApiGatewayResponse.build {
            this.statusCode = statusCode
        }
    }

    data class Body(val name: String? = null) {
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
