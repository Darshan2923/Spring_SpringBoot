# OpenapiSpecificationDarshan.AuthenticationApi

All URIs are relative to *http://localhost:8088/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authenticate**](AuthenticationApi.md#authenticate) | **POST** /auth/authenticate | 
[**confirm**](AuthenticationApi.md#confirm) | **GET** /auth/activate-account | 
[**register**](AuthenticationApi.md#register) | **POST** /auth/register | 



## authenticate

> AuthenticateResponse authenticate(authenticateRequest)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.AuthenticationApi();
let authenticateRequest = new OpenapiSpecificationDarshan.AuthenticateRequest(); // AuthenticateRequest | 
apiInstance.authenticate(authenticateRequest, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **authenticateRequest** | [**AuthenticateRequest**](AuthenticateRequest.md)|  | 

### Return type

[**AuthenticateResponse**](AuthenticateResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## confirm

> confirm(token)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.AuthenticationApi();
let token = "token_example"; // String | 
apiInstance.confirm(token, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **token** | **String**|  | 

### Return type

null (empty response body)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: Not defined


## register

> Object register(registrationRequest)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.AuthenticationApi();
let registrationRequest = new OpenapiSpecificationDarshan.RegistrationRequest(); // RegistrationRequest | 
apiInstance.register(registrationRequest, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **registrationRequest** | [**RegistrationRequest**](RegistrationRequest.md)|  | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

