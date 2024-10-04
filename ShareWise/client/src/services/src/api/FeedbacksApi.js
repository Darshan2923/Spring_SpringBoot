/**
 * Openapi specification - Darshan
 * OpenApi documentation for Spring security
 *
 * The version of the OpenAPI document: 1.0
 * Contact: walnut2918@gmail.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 *
 */


import ApiClient from "../ApiClient";
import FeedBackRequest from '../model/FeedBackRequest';
import PageResponseFeedBackResponse from '../model/PageResponseFeedBackResponse';

/**
* Feedbacks service.
* @module api/FeedbacksApi
* @version 1.0
*/
export default class FeedbacksApi {

    /**
    * Constructs a new FeedbacksApi. 
    * @alias module:api/FeedbacksApi
    * @class
    * @param {module:ApiClient} [apiClient] Optional API client implementation to use,
    * default to {@link module:ApiClient#instance} if unspecified.
    */
    constructor(apiClient) {
        this.apiClient = apiClient || ApiClient.instance;
    }


    /**
     * Callback function to receive the result of the findAllFeedbackByNotes operation.
     * @callback module:api/FeedbacksApi~findAllFeedbackByNotesCallback
     * @param {String} error Error message, if any.
     * @param {module:model/PageResponseFeedBackResponse} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {Number} notesId 
     * @param {Object} opts Optional parameters
     * @param {Number} [page = 0)] 
     * @param {Number} [size = 0)] 
     * @param {module:api/FeedbacksApi~findAllFeedbackByNotesCallback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link module:model/PageResponseFeedBackResponse}
     */
    findAllFeedbackByNotes(notesId, opts, callback) {
      opts = opts || {};
      let postBody = null;
      // verify the required parameter 'notesId' is set
      if (notesId === undefined || notesId === null) {
        throw new Error("Missing the required parameter 'notesId' when calling findAllFeedbackByNotes");
      }

      let pathParams = {
        'notes-id': notesId
      };
      let queryParams = {
        'page': opts['page'],
        'size': opts['size']
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = [];
      let accepts = ['application/json'];
      let returnType = PageResponseFeedBackResponse;
      return this.apiClient.callApi(
        '/notes/{notes-id}', 'GET',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }

    /**
     * Callback function to receive the result of the saveFeedBack1 operation.
     * @callback module:api/FeedbacksApi~saveFeedBack1Callback
     * @param {String} error Error message, if any.
     * @param {Number} data The data returned by the service call.
     * @param {String} response The complete HTTP response.
     */

    /**
     * @param {Object} opts Optional parameters
     * @param {module:model/FeedBackRequest} [feedBackRequest] 
     * @param {module:api/FeedbacksApi~saveFeedBack1Callback} callback The callback function, accepting three arguments: error, data, response
     * data is of type: {@link Number}
     */
    saveFeedBack1(opts, callback) {
      opts = opts || {};
      let postBody = opts['feedBackRequest'];

      let pathParams = {
      };
      let queryParams = {
      };
      let headerParams = {
      };
      let formParams = {
      };

      let authNames = ['bearerAuth'];
      let contentTypes = ['application/json'];
      let accepts = ['application/json'];
      let returnType = 'Number';
      return this.apiClient.callApi(
        '/', 'POST',
        pathParams, queryParams, headerParams, formParams, postBody,
        authNames, contentTypes, accepts, returnType, null, callback
      );
    }


}
