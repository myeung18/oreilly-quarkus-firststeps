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

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.annotations.Producer;
import org.kie.baaas.dfm.app.manager.KafkaService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

public class KafkaServiceProducer {
    @ConfigProperty(name = "baaas.dfm.kafka.service.type")
    String serviceType;


    final static KafkaService UNDEFINED = new UndefinedKafkaService();
    public static boolean isUndefined(KafkaService instance) {
        return instance == UNDEFINED;


    }

    @Produces
    @ApplicationScoped
    public KafkaService produceKafkaService() {
        KafkaService kc = UNDEFINED;
        if (KafkaServiceType.OPERATE_FIRST.equalValue(serviceType)) {
            kc = new OperateFirstKafkaService();
        } else if (KafkaServiceType.MANAGED_KAFKA.equalValue(serviceType)) {
            kc = new ManagedKafkaService();
        } else {
            //TODO : some exception
            throw new KafkaServiceNotSupportedException("");
        }
        return kc;
    }
}
