/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sparrow.datasource;

import java.sql.Connection;

/**
 * @author by harry
 */
public interface ConnectionContextHolder {
    void addOriginProxy(Connection proxy);
    /**
     * get datasource factory for refer class
     *
     * @return
     */
    DataSourceFactory getDataSourceFactory();

    /**
     * bind connection to current thread
     *
     * @param connection
     */
    void bindConnection(Connection connection);

    /**
     * unbind from the current thread
     *
     * @param connection
     */
    void unbindConnection(Connection connection);

    /**
     * get connection from current thread
     *
     * @param datasourceKey
     * @return
     */
    Connection getConnection(String datasourceKey);

    /**
     * remove all connection of current thread
     */
    void removeAll();
}
