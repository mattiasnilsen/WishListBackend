package com.wishlist.common

import com.amazonaws.services.lambda.runtime.ClientContext

data class ApigatewayRequest(var input: Input? = null) {
    data class Authorizer(var principalId:String? = null, var claims:MutableMap<String, String>? = null)

    data class Input(var path: String? = null,
                     var headers: MutableMap<String, String>? = null,
                     var pathParameters: MutableMap<String, String>? = null,
                     var requestContext: RequestContext? = null,
                     var resource: String? = null,
                     var httpMethod: String? = null,
                     var queryStringParameters: MutableMap<String, String>? = null,
                     var stageVariables: MutableMap<String, String>? = null,
                     var request: Map<String, Any>? = null,
                     var clientContext: ClientContext? = null,
                     var callerContext: CallerContext? = null

    )

    data class RequestContext(var accountId: String? = null,
                              var resourceId: String? = null,
                              var stage: String? = null,
                              var requestId: String? = null,
                              var identity: MutableMap<String, String>? = null,
                              var authorizer: Authorizer? = null,
                              var resourcePath: String? = null,
                              var httpMethod: String? = null,
                              var apiId: String? = null,
                              var userName: String? = null,
                              var clientContext: ClientContext? = null,
                              var callerContext: CallerContext? = null)

    data class CallerContext(
            var awsSdkVersion: String? = null,
            var clientId: String? = null
    )
}
