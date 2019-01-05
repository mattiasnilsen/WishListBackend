package com.wishlist.common

data class ApiGatewayRequest(var resource: String? = null,
                             var path: String? = null,
                             var httpMethod: String? = null,
                             var headers: MutableMap<String, String>? = null,
                             var queryStringParameters: MutableMap<String, String>? = null,
                             var multiValueQueryStringParameters: MutableMap<String, String>? = null,
                             var pathParameters: MutableMap<String, String>? = null,
                             var stageVariables: MutableMap<String, String>? = null,
                             var requestContext: RequestContext? = null,
                             var body: String? = null,
                             var isBase64Encoded: Boolean? = null) {

    data class RequestContext(var resourceId: String? = null,
                              var authorizer: Authorizer? = null,
                              var resourcePath: String? = null,
                              var httpMethod: String? = null,
                              var extendedRequestId: String? = null,
                              var requestTime: String? = null,
                              var path: String? = null,
                              var accountId: String? = null,
                              var protocol: String? = null,
                              var stage: String? = null,
                              var domainPrefix: String? = null,
                              var requestTimeEpoch: Long? = null,
                              var requestId: String? = null,
                              var identity: Identity? = null,
                              var domainName: String? = null,
                              var apiId: String? = null)

    data class Authorizer(var principalId: String? = null, var claims: Claims? = null)

    data class Claims(var sub: String? = null,
                      var aud: String? = null,
                      var emailVerified: Boolean? = null,
                      var tokenUse: String? = null,
                      var authTime: String? = null,
                      var iss: String? = null,
                      var cognitoUsername: String? = null,
                      var exp: String? = null,
                      var iat: String? = null,
                      var email: String? = null)

    data class Identity(var cognitoIdentityPoolId: String? = null,
                        var accountId: String? = null,
                        var cognitoIdentityId: String? = null,
                        var caller: String? = null,
                        var sourceIp: String? = null,
                        var accessKey: String? = null,
                        var cognitoAuthenticationType: String? = null,
                        var cognitoAuthenticationProvider: String? = null,
                        var userArn: String? = null,
                        var userAgent: String? = null,
                        var user: String? = null)

    data class CallerContext(
            var awsSdkVersion: String? = null,
            var clientId: String? = null
    )
}
