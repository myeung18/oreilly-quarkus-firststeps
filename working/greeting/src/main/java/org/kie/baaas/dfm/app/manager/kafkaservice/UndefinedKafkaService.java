/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.kie.baaas.dfm.app.manager.kafkaservice;

import io.quarkus.security.credential.Credential;
import org.kie.baaas.dfm.app.manager.KafkaService;

/**
 * This is a dummy class for the purpose of returning code:400 back to the client as the Kafka Client is not supported.
 */
public class UndefinedKafkaService implements KafkaService {

    @Override
    public Credential getCustomerCredential(String customerId) {
        throw new UnsupportedOperationException("UndefinedKafkaService");
    }
}
