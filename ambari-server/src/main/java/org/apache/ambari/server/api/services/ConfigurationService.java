/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.api.services;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.ambari.server.api.resources.ConfigurationResourceDefinition;
import org.apache.ambari.server.api.resources.ResourceDefinition;

/**
 * Service responsible for services resource requests.
 */
public class ConfigurationService extends BaseService {
  /**
   * Parent cluster name.
   */
  private String m_clusterName;

  /**
   * Constructor.
   *
   * @param clusterName cluster id
   */
  public ConfigurationService(String clusterName) {
    m_clusterName = clusterName;
  }

  /**
   * Handles URL: /clusters/{clusterId}/configurations
   * Get all services for a cluster.
   *
   * @param headers http headers
   * @param ui      uri info
   * @return service collection resource representation
   */
  @GET
  @Produces("text/plain")
  public Response getConfigurations(@Context HttpHeaders headers, @Context UriInfo ui) {
    return handleRequest(headers, null, ui, Request.Type.GET,
        createResourceDefinition(null, m_clusterName));
  }

  /**
   * Handles URL: /clusters/{clusterId}/configurations
   * The body should contain:
   * <pre>
   * {
   *     "type":"type_string",
   *     "tag":"version_tag",
   *     "properties":
   *     {
   *         "key1":"value1",
   *         // ...
   *         "keyN":"valueN"
   *     }
   * }
   * </pre>
   *
   * To create multiple configurations is a request, provide an array of configuration properties.
   *
   * @param headers http headers
   * @param ui      uri info
   * @return status code only, 201 if successful
   */
  @POST
  @Produces("text/plain")
  public Response createConfigurations(String body,@Context HttpHeaders headers, @Context UriInfo ui) {

    return handleRequest(headers, body, ui, Request.Type.POST,
        createResourceDefinition(null, m_clusterName));
  }

  /**
   * Create a service resource definition.
   *
   * @param configType  configuration type
   * @param clusterName cluster name
   *
   * @return a service resource definition
   */
  ResourceDefinition createResourceDefinition(String configType, String clusterName) {
    return new ConfigurationResourceDefinition(configType, null, clusterName);
  }
}
