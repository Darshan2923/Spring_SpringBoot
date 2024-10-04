# OpenapiSpecificationDarshan.NotesApi

All URIs are relative to *http://localhost:8088/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**approvereturnBorrowNotes**](NotesApi.md#approvereturnBorrowNotes) | **PATCH** /notes/borrow/return/approve/{notes-id} | 
[**borrowNotes**](NotesApi.md#borrowNotes) | **POST** /notes/borrowed/{notes-id} | 
[**findAllBorrowedNotes**](NotesApi.md#findAllBorrowedNotes) | **GET** /notes/borrowed | 
[**findAllNotes**](NotesApi.md#findAllNotes) | **GET** /notes | 
[**findAllNotesByOwner**](NotesApi.md#findAllNotesByOwner) | **GET** /notes/owner | 
[**findAllReturnedNotes**](NotesApi.md#findAllReturnedNotes) | **GET** /notes/returned | 
[**findNotesById**](NotesApi.md#findNotesById) | **GET** /notes/{book-id} | 
[**returnBorrowNotes**](NotesApi.md#returnBorrowNotes) | **PATCH** /notes/borrow/return/{notes-id} | 
[**saveNote**](NotesApi.md#saveNote) | **POST** /notes/path | 
[**updateArchivedStatus**](NotesApi.md#updateArchivedStatus) | **PATCH** /notes/archived/{notes-id} | 
[**updateShareableStatus**](NotesApi.md#updateShareableStatus) | **PATCH** /notes/shareable/{notes-id} | 
[**uploadNotesCoverPage**](NotesApi.md#uploadNotesCoverPage) | **POST** /notes/cover/{notes-id} | 



## approvereturnBorrowNotes

> Number approvereturnBorrowNotes(notesId)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesId = 56; // Number | 
apiInstance.approvereturnBorrowNotes(notesId, (error, data, response) => {
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

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## borrowNotes

> Number borrowNotes(notesId)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesId = 56; // Number | 
apiInstance.borrowNotes(notesId, (error, data, response) => {
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

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## findAllBorrowedNotes

> PageResponseBorrowedNotesResponse findAllBorrowedNotes(opts)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let opts = {
  'page': 0, // Number | 
  'size': 10 // Number | 
};
apiInstance.findAllBorrowedNotes(opts, (error, data, response) => {
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
 **page** | **Number**|  | [optional] [default to 0]
 **size** | **Number**|  | [optional] [default to 10]

### Return type

[**PageResponseBorrowedNotesResponse**](PageResponseBorrowedNotesResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## findAllNotes

> PageResponseNotesResponse findAllNotes(opts)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let opts = {
  'page': 0, // Number | 
  'size': 10 // Number | 
};
apiInstance.findAllNotes(opts, (error, data, response) => {
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
 **page** | **Number**|  | [optional] [default to 0]
 **size** | **Number**|  | [optional] [default to 10]

### Return type

[**PageResponseNotesResponse**](PageResponseNotesResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## findAllNotesByOwner

> PageResponseNotesResponse findAllNotesByOwner(opts)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let opts = {
  'page': 0, // Number | 
  'size': 10 // Number | 
};
apiInstance.findAllNotesByOwner(opts, (error, data, response) => {
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
 **page** | **Number**|  | [optional] [default to 0]
 **size** | **Number**|  | [optional] [default to 10]

### Return type

[**PageResponseNotesResponse**](PageResponseNotesResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## findAllReturnedNotes

> PageResponseBorrowedNotesResponse findAllReturnedNotes(opts)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let opts = {
  'page': 0, // Number | 
  'size': 10 // Number | 
};
apiInstance.findAllReturnedNotes(opts, (error, data, response) => {
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
 **page** | **Number**|  | [optional] [default to 0]
 **size** | **Number**|  | [optional] [default to 10]

### Return type

[**PageResponseBorrowedNotesResponse**](PageResponseBorrowedNotesResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## findNotesById

> NotesResponse findNotesById(bookId)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let bookId = 56; // Number | 
apiInstance.findNotesById(bookId, (error, data, response) => {
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
 **bookId** | **Number**|  | 

### Return type

[**NotesResponse**](NotesResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## returnBorrowNotes

> Number returnBorrowNotes(notesId)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesId = 56; // Number | 
apiInstance.returnBorrowNotes(notesId, (error, data, response) => {
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

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## saveNote

> Number saveNote(notesRequest)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesRequest = new OpenapiSpecificationDarshan.NotesRequest(); // NotesRequest | 
apiInstance.saveNote(notesRequest, (error, data, response) => {
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
 **notesRequest** | [**NotesRequest**](NotesRequest.md)|  | 

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json


## updateArchivedStatus

> Number updateArchivedStatus(notesId)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesId = 56; // Number | 
apiInstance.updateArchivedStatus(notesId, (error, data, response) => {
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

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## updateShareableStatus

> Number updateShareableStatus(notesId)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesId = 56; // Number | 
apiInstance.updateShareableStatus(notesId, (error, data, response) => {
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

### Return type

**Number**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


## uploadNotesCoverPage

> Object uploadNotesCoverPage(notesId, file)



### Example

```javascript
import OpenapiSpecificationDarshan from 'openapi_specification_darshan';
let defaultClient = OpenapiSpecificationDarshan.ApiClient.instance;
// Configure Bearer (JWT) access token for authorization: bearerAuth
let bearerAuth = defaultClient.authentications['bearerAuth'];
bearerAuth.accessToken = "YOUR ACCESS TOKEN"

let apiInstance = new OpenapiSpecificationDarshan.NotesApi();
let notesId = 56; // Number | 
let file = "/path/to/file"; // File | 
apiInstance.uploadNotesCoverPage(notesId, file, (error, data, response) => {
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
 **file** | **File**|  | 

### Return type

**Object**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: multipart/form-data
- **Accept**: application/json

