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

package org.acme;

import org.acme.dao.Training;
import org.acme.dao.TrainingRepository;
import org.hibernate.engine.spi.Managed;
import org.kie.baaas.dfm.app.manager.KafkaService;
import org.kie.baaas.dfm.app.manager.kafkaservice.KafkaServiceProducer;
import org.kie.baaas.dfm.app.manager.kafkaservice.ManagedKafkaService;
import org.kie.baaas.dfm.app.manager.kafkaservice.UndefinedKafkaService;

import javax.inject.Inject;
import javax.ws.rs.*;

import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/training")
public class TrainingResource {              // (1)
    @Inject
    KafkaService kafkaService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Training> listTrainings() {
        List<Training> res = Training.listAll();
        System.out.println("record >>> " + res);

        System.out.println("class: " + kafkaService);
        System.out.println("class: " + (KafkaServiceProducer.isUndefined(kafkaService)));
        return res;                   // (2)
    }

    @Inject
    TrainingRepository repository;                               // (1)

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/repository")
    public Training postRepositoryTraining(Training training) {
        repository.persist(training);                            // (2)

        return training;
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Training postTraining(Training training) {
        training.persist();                              // (3)

        return training;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/repository/{name}")
    public Training getRepositoryTraining(@PathParam("name") String name) {
        return repository.findByName(name);
    }

}