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
 # ********************************************************************************/

package com.i2group.auth.rest.transport.auth;

/** The RFC7807 response body object. */
public class ProblemDetails {
  public String title;
  public String type;
  public int status;
  public String detail;
  public String instance;
}
