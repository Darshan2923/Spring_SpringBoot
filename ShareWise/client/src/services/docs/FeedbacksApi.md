# OpenapiSpecificationDarshan.FeedbacksApi

All URIs are relative to *http://localhost:8088/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**findAllFeedbackByNotes**](FeedbacksApi.md#findAllFeedbackByNotes) | **GET** /notes/{notes-id} | 
[**saveFeedBack1**](FeedbacksApi.md#saveFeedBack1) | **POST** / | 



## findAllFeedbackByNotes

> PageResponseFeedBackResponse findAllFeedbackByNotes(notesId, opts)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.FeedbacksApi();
let notesId = 56; // Number | 
let opts = {
  'page': 0, // Number | 
  'size': 0 // Number | 
};
apiInstance.findAllFeedbackByNotes(notesId, opts, (error, data, response) => {
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
 **notesId** | **Number**|  | 
 **page** | **Number**|  | [optional] [default to 0]
 **size** | **Number**|  | [optional] [default to 0]

### Return type

[**PageResponseFeedBackResponse**](PageResponseFeedBackResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## saveFeedBack1

> Number saveFeedBack1(opts)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.FeedbacksApi();
let opts = {
  'feedBackRequest': new OpenapiSpecificationDarshan.FeedBackRequest() // FeedBackRequest | 
};
apiInstance.saveFeedBack1(opts, (error, data, response) => {
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
 **feedBackRequest** | [**FeedBackRequest**](FeedBackRequest.md)|  | [optional] 

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

