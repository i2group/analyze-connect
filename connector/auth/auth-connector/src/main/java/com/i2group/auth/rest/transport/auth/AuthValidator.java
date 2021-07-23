/********************************************************************************
 # * Licensed Materials - Property of IBM
 # * (C) Copyright IBM Corporation 2021. All Rights Reserved
 # *
 # * This program and the accompanying materials are made available under the
 # * terms of the Eclipse Public License 2.0 which is available at
 # * http://www.eclipse.org/legal/epl-2.0.
 # *
 # * US Government Users Restricted Rights - Use, duplication or
 # * disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 # *
 #
 ********************************************************************************/

package com.i2group.auth.rest.transport.auth;

/**
 * Handles validation of authorization headers.
 */
public class AuthValidator {
  public boolean valid;
  public String title;
  public String details = null;

  private final static String AUTH_HEADER_PREFIX = "Bearer ";

  public AuthValidator(boolean valid, String title, String details) {
    this.valid = valid;
    this.title = title;
    this.details = details;
  }

  public AuthValidator(boolean valid, String title) {
    this.valid = valid;
    this.title = title;
  }

  /**
   * Validates the authorizaton headers of requests.
   *
   * @param auth The value of the Authorization header.
   * @return True if valid.
   */
  public static AuthValidator validateAuth(String auth) {
    if (auth == null) {
      return new AuthValidator(false, "Authorization header not provided.");
    }
    if (!auth.startsWith(AUTH_HEADER_PREFIX)) {
      return new AuthValidator(false, "Invalid authorization header.");
    }
    final String token = auth.replace(AUTH_HEADER_PREFIX, "");
    return TokenManager.verifyToken(token);
  }
}
