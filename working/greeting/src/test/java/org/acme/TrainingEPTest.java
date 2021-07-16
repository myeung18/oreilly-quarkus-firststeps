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

import io.quarkus.test.junit.QuarkusTest;
import org.acme.api.Student;
import org.acme.api.Training;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TrainingEPTest {
    @Test
    void testEndPoint() {
        Training t = new Training();
        t.setName("KKKKK");

        List<Student> lst = new ArrayList<>();
        Student s1 = new Student();
        s1.setName("J. Fault");
        Student s2 = new Student();
        s1.setName("K. Jane");

        lst.add(s1);
        lst.add(s2);

        t.setStudents(lst);

        given().when()
                .contentType(MediaType.APPLICATION_JSON).body(t)
                .when().post("/training").then().statusCode(200);


        Training[] tr = given().given().get("/training").then().statusCode(200).extract().as(Training[].class);

        for (Training tx : tr) {
            System.out.printf("data: " + tx);
        }

    }

}
