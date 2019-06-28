package com.expedia.adaptivealerting.modelservice.web;

import com.expedia.adaptivealerting.modelservice.entity.Detector;
import com.expedia.adaptivealerting.modelservice.service.DetectorService;
import com.expedia.adaptivealerting.modelservice.test.ObjectMother;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class DetectorControllerTest {

    @InjectMocks
    private DetectorController controller;

    @Mock
    private DetectorService detectorService;

    @Mock
    private Detector detector;

    @Mock
    private List<Detector> detectors;

    private Map<String, Object> illegalDetectorParams;

    @Before
    public void setUp() {
        this.controller = new DetectorController();
        MockitoAnnotations.initMocks(this);
        initTestObjects();
        when(detectorService.findByUuid(anyString())).thenReturn(detector);
        when(detectorService.getLastUpdatedDetectors(anyLong())).thenReturn(detectors);
    }

    private void initTestObjects() {
        val mom = ObjectMother.instance();
        illegalDetectorParams = mom.getIllegalDetectorParams();
    }


    @Test
    public void testFindByUuid() {
        Detector actualDetector = controller.findByUuid("uuid");
        assertNotNull(actualDetector);
    }

    @Test
    public void testFindByCreatedBy() {
        List<Detector> actualDetectors = controller.findByCreatedBy("kashah");
        assertNotNull(actualDetectors);
    }

    @Test
    public void testToggleDetector() {
        controller.toggleDetector("uuid", true);
    }

    @Test
    public void testGetLastUpdatedDetectors() {
        int interval = 5;
        List<Detector> actualDetectors = controller.getLastUpdatedDetectors(interval);
        assertNotNull(actualDetectors);
        assertSame(detectors, actualDetectors);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDetectorNullValues() {
        Detector detector1 = new Detector();
        detector1.setCreatedBy("user");
        controller.createDetector(detector1);

        Detector detector2 = new Detector();
        detector2.setType("constant-detector");
        controller.createDetector(detector2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDetectorIllegalThresholds() {
        controller.createDetector(getIllegalParamsDetector());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDetectorNullValues() {
        Detector detector1 = new Detector();
        detector1.setCreatedBy("user");
        controller.updateDetector("", detector1);

        Detector detector2 = new Detector();
        detector2.setType("constant-detector");
        controller.updateDetector("", detector2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateDetectorIllegalThresholds() {
        controller.updateDetector("", getIllegalParamsDetector());
    }

    private Detector getIllegalParamsDetector() {
        Detector detector = new Detector();
        detector.setCreatedBy("user");
        detector.setType("constant-detector");

        Map<String, Object> detectorConfig = new HashMap<>();
        detectorConfig.put("params", illegalDetectorParams);
        detector.setDetectorConfig(detectorConfig);
        return detector;
    }
}