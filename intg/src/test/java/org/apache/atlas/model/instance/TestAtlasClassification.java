/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.atlas.model.instance;

import org.apache.atlas.model.ModelTestUtil;
import org.apache.atlas.model.typedef.AtlasClassificationDef;
import org.apache.atlas.type.AtlasType;
import org.apache.atlas.type.AtlasClassificationType;
import org.apache.atlas.type.AtlasTypeRegistry;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class TestAtlasClassification {

    @Test
    public void testClassificationSerDe() {
        AtlasClassificationDef classificationDef = ModelTestUtil.getClassificationDef();
        AtlasTypeRegistry      typeRegistry      = ModelTestUtil.getTypesRegistry();
        AtlasType              dataType          = typeRegistry.getType(classificationDef.getName());

        assertTrue(dataType instanceof  AtlasClassificationType);

        AtlasClassification ent1 = ModelTestUtil.newClassification(classificationDef, typeRegistry);

        String jsonString = AtlasType.toJson(ent1);

        AtlasClassification ent2 = AtlasType.fromJson(jsonString, AtlasClassification.class);

        ((AtlasClassificationType)dataType).normalizeAttributeValues(ent2);

        assertEquals(ent2, ent1, "Incorrect serialization/deserialization of AtlasClassification");
    }

    @Test
    public void testClassificationSerDeWithSuperType() {
        AtlasClassificationDef classificationDef = ModelTestUtil.getClassificationDefWithSuperType();
        AtlasTypeRegistry      typeRegistry      = ModelTestUtil.getTypesRegistry();
        AtlasType              dataType          = typeRegistry.getType(classificationDef.getName());

        assertTrue(dataType instanceof AtlasClassificationType);

        AtlasClassification ent1 =  ((AtlasClassificationType)dataType).createDefaultValue();

        String jsonString = AtlasType.toJson(ent1);

        AtlasClassification ent2 = AtlasType.fromJson(jsonString, AtlasClassification.class);

        ((AtlasClassificationType)dataType).normalizeAttributeValues(ent2);

        assertEquals(ent2, ent1, "Incorrect serialization/deserialization of AtlasClassification with superType");
    }

    @Test
    public void testClassificationSerDeWithSuperTypes() {
        AtlasClassificationDef classificationDef = ModelTestUtil.getClassificationDefWithSuperTypes();
        AtlasTypeRegistry      typeRegistry      = ModelTestUtil.getTypesRegistry();
        AtlasType              dataType          = typeRegistry.getType(classificationDef.getName());

        assertTrue(dataType instanceof  AtlasClassificationType);

        AtlasClassification ent1 =  ((AtlasClassificationType)dataType).createDefaultValue();

        String jsonString = AtlasType.toJson(ent1);

        AtlasClassification ent2 = AtlasType.fromJson(jsonString, AtlasClassification.class);

        ((AtlasClassificationType)dataType).normalizeAttributeValues(ent2);

        assertEquals(ent2, ent1, "Incorrect serialization/deserialization of AtlasClassification with superTypes");
    }
}
