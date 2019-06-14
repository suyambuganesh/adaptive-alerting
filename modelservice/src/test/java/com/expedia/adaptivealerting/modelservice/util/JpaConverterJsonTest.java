/*
 * Copyright 2018-2019 Expedia Group, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.expedia.adaptivealerting.modelservice.util;

import com.expedia.adaptivealerting.modelservice.test.ObjectMother;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class JpaConverterJsonTest {

    /* Class under test */
    @InjectMocks
    private JpaConverterJson convertor;

    @Mock
    private ObjectMapper mapper;

    private String expectedString;
    private Map expectedObject;

    @Before
    public void setUp() throws Exception {
        this.convertor = new JpaConverterJson();
        MockitoAnnotations.initMocks(this);
        initTestObjects();
        initDependencies();
    }

    @Test
    public void testConvertToEntityAttribute() {
        Object actualObject = convertor.convertToEntityAttribute(expectedString);
        assertEquals(expectedObject, actualObject);
    }

    @Test
    public void testConvertToDatabaseColumn() {
        Object actualString = convertor.convertToDatabaseColumn(expectedObject);
        assertEquals(expectedString, actualString);
    }

    private void initTestObjects() {
        val mom = ObjectMother.instance();
        expectedObject = mom.getTestObject();
        expectedString = mom.getTestString();
    }

    private void initDependencies() {
        try {
            Mockito.when(mapper.writeValueAsString(expectedObject)).thenReturn("");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Mockito.when(mapper.readValue("", Map.class)).thenReturn(expectedObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
