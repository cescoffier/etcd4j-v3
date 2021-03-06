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

import java.util.concurrent.CompletableFuture;

import com.github.lburgazzoli.etcd.v3.api.KVGrpc;
import com.google.protobuf.ByteString;

class PutRequest extends AbstractRequest<KVGrpc.KVVertxStub, PutResponse> {
    private final ByteString key;
    private final ByteString value;

    PutRequest(Stub<KVGrpc.KVVertxStub> stub, ByteString key, ByteString value) {
       super(stub);

        this.key = key;
        this.value = value;
    }

    @Override
    protected void execute(KVGrpc.KVVertxStub stub, CompletableFuture<PutResponse> future) {
        com.github.lburgazzoli.etcd.v3.api.PutRequest request =
            com.github.lburgazzoli.etcd.v3.api.PutRequest.newBuilder()
                .setKey(key)
                .setValue(value)
                .build();

        stub.put(request, h -> {
            if (h.succeeded()) {
                future.complete(new PutResponse(h.result()));
            } else {
                future.completeExceptionally(h.cause());
            }
        });
    }
}
