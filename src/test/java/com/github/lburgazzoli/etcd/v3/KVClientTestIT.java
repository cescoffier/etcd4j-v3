/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lburgazzoli.etcd.v3;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KVClientTestIT {
    private static final Logger LOGGER = LoggerFactory.getLogger(KVClientTestIT.class);
    private static final String ETCD_HOST = System.getProperty("etcd.host", "localhost");
    private static final int ETCD_PORT = Integer.getInteger("etcd.port", 2379);
    private static final String ETCD_URI = String.format("%s:%d", ETCD_HOST, ETCD_PORT);

    @Test
    public void test() {
        Etcd etcd = Etcd.builder().endpoints(ETCD_URI).build();
        LOGGER.info("PUT: {}", etcd.kvClient().put("key", "value").join());
        LOGGER.info("RANGE: {}", etcd.kvClient().range("key").join());
    }
}