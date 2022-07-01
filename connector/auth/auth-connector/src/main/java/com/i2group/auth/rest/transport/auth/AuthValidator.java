/********************************************************************************
# MIT License
#
# © N.Harris Computer Corporation (2022) 
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
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
