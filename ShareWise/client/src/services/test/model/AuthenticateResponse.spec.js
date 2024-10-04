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

(function(root, factory) {
  if (typeof define === 'function' && define.amd) {
    // AMD.
    define(['expect.js', process.cwd()+'/src/index'], factory);
  } else if (typeof module === 'object' && module.exports) {
    // CommonJS-like environments that support module.exports, like Node.
    factory(require('expect.js'), require(process.cwd()+'/src/index'));
  } else {
    // Browser globals (root is window)
    factory(root.expect, root.OpenapiSpecificationDarshan);
  }
}(this, function(expect, OpenapiSpecificationDarshan) {
  'use strict';

  var instance;

  beforeEach(function() {
    instance = new OpenapiSpecificationDarshan.AuthenticateResponse();
  });

  var getProperty = function(object, getter, property) {
    // Use getter method if present; otherwise, get the property directly.
    if (typeof object[getter] === 'function')
      return object[getter]();
    else
      return object[property];
  }

  var setProperty = function(object, setter, property, value) {
    // Use setter method if present; otherwise, set the property directly.
    if (typeof object[setter] === 'function')
      object[setter](value);
    else
      object[property] = value;
  }

  describe('AuthenticateResponse', function() {
    it('should create an instance of AuthenticateResponse', function() {
      // uncomment below and update the code to test AuthenticateResponse
      //var instance = new OpenapiSpecificationDarshan.AuthenticateResponse();
      //expect(instance).to.be.a(OpenapiSpecificationDarshan.AuthenticateResponse);
    });

    it('should have the property token (base name: "token")', function() {
      // uncomment below and update the code to test the property token
      //var instance = new OpenapiSpecificationDarshan.AuthenticateResponse();
      //expect(instance).to.be();
    });

  });

}));