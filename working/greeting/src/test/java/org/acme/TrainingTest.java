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

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.acme.dao.Training;
import org.acme.dao.TrainingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TrainingTest {


    @InjectMock                                                             // (1)
    TrainingRepository repository;

    @Test
    public void testDataRepository() {
        // Mock a training
        Training training = new Training();
        training.name = training.name = "Quarkus Deep Dive";

        Mockito.when(repository.findById(10L)).thenReturn(training);
        Mockito.when(repository.count()).thenReturn(1L);
        Mockito.when(repository.listAll())
                .thenReturn(Arrays.asList(training));
        Mockito.when(repository
                .findByName("Quarkus Deep Dive"))                           // (2)
                .thenReturn(training);

        // Make assertions
        Assertions.assertSame(training, repository.findById(10L));
        Assertions.assertSame(repository.count(), 1L);
        Assertions.assertSame(repository.listAll().get(0), training);
        Assertions.assertSame(repository.findByName("Quarkus Deep Dive"),   // (3)
                training);
        Assertions.assertTrue("Quarkus Deep Dive!".equals(training.name));

        Mockito.verify(repository).count();                                 // (4)
        Mockito.verify(repository).listAll();
        Mockito.verify(repository).findById(Mockito.any());
        Mockito.verify(repository).findByName(Mockito.any());
    }


    @Test
    public void testActiveRecord() {
        PanacheMock.mock(Training.class);                             // (1)

        // Mock a training
        Training training = new Training();

        training.name = "Quarkus Deep Dive";                             // (1)

        Mockito.when(Training.findById(10L)).thenReturn(training);
        Mockito.when(Training.count()).thenReturn(1L);
        Mockito.when(Training.listAll())
                .thenReturn(Arrays.asList(training));

        // Make assertions
        Assertions.assertSame(training, Training.findById(10L));
        Assertions.assertSame(Training.listAll().size(), 1);
        Assertions.assertSame(Training.count(), 1L);

        Assertions.assertTrue("Quarkus Deep Dive!".equals(training.name)); // (2)

        PanacheMock.verify(Training.class).count();                   // (2)
        PanacheMock.verify(Training.class).listAll();
        PanacheMock.verify(Training.class,
                Mockito.atLeastOnce()).findById(Mockito.any());
    }
}