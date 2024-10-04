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
 * The AuthenticateResponse model module.
 * @module model/AuthenticateResponse
 * @version 1.0
 */
class AuthenticateResponse {
    /**
     * Constructs a new <code>AuthenticateResponse</code>.
     * @alias module:model/AuthenticateResponse
     */
    constructor() { 
        
        AuthenticateResponse.initialize(this);
    }

    /**
     * Initializes the fields of this object.
     * This method is used by the constructors of any subclasses, in order to implement multiple inheritance (mix-ins).
     * Only for internal use.
     */
    static initialize(obj) { 
    }

    /**
     * Constructs a <code>AuthenticateResponse</code> from a plain JavaScript object, optionally creating a new instance.
     * Copies all relevant properties from <code>data</code> to <code>obj</code> if supplied or a new instance if not.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @param {module:model/AuthenticateResponse} obj Optional instance to populate.
     * @return {module:model/AuthenticateResponse} The populated <code>AuthenticateResponse</code> instance.
     */
    static constructFromObject(data, obj) {
        if (data) {
            obj = obj || new AuthenticateResponse();

            if (data.hasOwnProperty('token')) {
                obj['token'] = ApiClient.convertToType(data['token'], 'String');
            }
        }
        return obj;
    }

    /**
     * Validates the JSON data with respect to <code>AuthenticateResponse</code>.
     * @param {Object} data The plain JavaScript object bearing properties of interest.
     * @return {boolean} to indicate whether the JSON data is valid with respect to <code>AuthenticateResponse</code>.
     */
    static validateJSON(data) {
        // ensure the json data is a string
        if (data['token'] && !(typeof data['token'] === 'string' || data['token'] instanceof String)) {
            throw new Error("Expected the field `token` to be a primitive type in the JSON string but got " + data['token']);
        }

        return true;
    }


}



/**
 * @member {String} token
 */
AuthenticateResponse.prototype['token'] = undefined;






export default AuthenticateResponse;

