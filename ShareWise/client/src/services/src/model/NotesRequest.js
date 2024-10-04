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

import ApiClient from '../ApiClient';

/**
 * The NotesRequest model module.
 * @module model/NotesRequest
 * @version 1.0
 */
class NotesRequest {
    /**
     * Constructs a new <code>NotesRequest</code>.
     * @alias module:model/NotesRequest
     * @param title {String} 
     * @param authorName {String} 
     * @param synopsis {String} 
     */
    constructor(title, authorName, synopsis) { 
        
        NotesRequest.initialize(this, title, authorName, synopsis);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj, title, authorName, synopsis) { 
        obj['title'] = title;
        obj['authorName'] = authorName;
        obj['synopsis'] = synopsis;
    }

    /**
     * Constructs a <code>NotesRequest</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/NotesRequest} obj Optional instance to populate.
     * @return {module:model/NotesRequest} The populated <code>NotesRequest</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new NotesRequest();

            if (data.hasOwnProperty('id')) {
                obj['id'] = ApiClient.convertToType(data['id'], 'Number');
            }
            if (data.hasOwnProperty('title')) {
                obj['title'] = ApiClient.convertToType(data['title'], 'String');
            }
            if (data.hasOwnProperty('authorName')) {
                obj['authorName'] = ApiClient.convertToType(data['authorName'], 'String');
            }
            if (data.hasOwnProperty('synopsis')) {
                obj['synopsis'] = ApiClient.convertToType(data['synopsis'], 'String');
            }
            if (data.hasOwnProperty('shareable')) {
                obj['shareable'] = ApiClient.convertToType(data['shareable'], 'Boolean');
            }
        }
        return obj;
    }

    /**
     * Validates the JSON data with respect to <code>NotesRequest</code>.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @return {boolean} to indicate whether the JSON data is valid with respect to <code>NotesRequest</code>.
     */
    static validateJSON(data) {
        // check to make sure all required properties are present in the JSON string
        for (const property of NotesRequest.RequiredProperties) {
            if (!data.hasOwnProperty(property)) {
                throw new Error("The required field `" + property + "` is not found in the JSON data: " + JSON.stringify(data));
            }
        }
        // ensure the json data is a string
        if (data['title'] && !(typeof data['title'] === 'string' || data['title'] instanceof String)) {
            throw new Error("Expected the field `title` to be a primitive type in the JSON string but got " + data['title']);
        }
        // ensure the json data is a string
        if (data['authorName'] && !(typeof data['authorName'] === 'string' || data['authorName'] instanceof String)) {
            throw new Error("Expected the field `authorName` to be a primitive type in the JSON string but got " + data['authorName']);
        }
        // ensure the json data is a string
        if (data['synopsis'] && !(typeof data['synopsis'] === 'string' || data['synopsis'] instanceof String)) {
            throw new Error("Expected the field `synopsis` to be a primitive type in the JSON string but got " + data['synopsis']);
        }

        return true;
    }


}

NotesRequest.RequiredProperties = ["title", "authorName", "synopsis"];

/**
 * @member {Number} id
 */
NotesRequest.prototype['id'] = undefined;

/**
 * @member {String} title
 */
NotesRequest.prototype['title'] = undefined;

/**
 * @member {String} authorName
 */
NotesRequest.prototype['authorName'] = undefined;

/**
 * @member {String} synopsis
 */
NotesRequest.prototype['synopsis'] = undefined;

/**
 * @member {Boolean} shareable
 */
NotesRequest.prototype['shareable'] = undefined;






export default NotesRequest;

