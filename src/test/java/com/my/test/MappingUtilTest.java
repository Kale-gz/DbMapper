package com.my.test;

import com.my.source.MappedObject;
import com.my.source.MappingUtil;
import com.my.source.ObjectToMap;
import org.junit.jupiter.api.Test;



import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;

/**
 * Test Class for {@link com.my.source.MappingUtil}
 */
public class MappingUtilTest {

    @Test
    void simpleTestCase() throws ClassNotFoundException, NoSuchMethodException {
        MappingUtil mappingUtil = new MappingUtil();

        ObjectToMap objectToMap = new ObjectToMap();
        objectToMap.setField1("field1");
        objectToMap.setField10("field10");
        objectToMap.setField12("field12");
        objectToMap.setField18("field18");

        List<MappedObject> mappedObjects = mappingUtil.map(objectToMap);

        assertThat(mappedObjects, hasSize(2));
        assertThat(mappedObjects.get(0).getAttr1().equals(objectToMap.getField10()), is(Boolean.TRUE));
        assertThat(mappedObjects.get(0).getAttr2().equals(objectToMap.getField12()), is(Boolean.TRUE));
        assertThat(mappedObjects.get(1).getAttr1().equals(objectToMap.getField18()), is(Boolean.TRUE));
        assertThat(mappedObjects.get(1).getAttr2().equals(objectToMap.getField1()), is(Boolean.TRUE));
    }

}
