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

package org.acme.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Training {
    @JsonbProperty
    String name;

    @JsonbProperty("students")
    public List<Student> students;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // TODO: 7/15/21

    @Override
    public String toString() {
        return "Training{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}
