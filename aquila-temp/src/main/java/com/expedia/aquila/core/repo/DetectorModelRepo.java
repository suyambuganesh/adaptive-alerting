/*
 * Copyright 2018 Expedia Group, Inc.
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
package com.expedia.aquila.core.repo;

import com.expedia.aquila.detect.AquilaAnomalyDetector;
import com.typesafe.config.Config;

import java.util.UUID;

// TODO Apply connector/service pattern as we do with the data case.

/**
 * @author Willie Wheeler
 * @author Karan Shah
 */
public interface DetectorModelRepo {
    
    void init(Config config);
    
    void save(AquilaAnomalyDetector detector);
    
    AquilaAnomalyDetector load(UUID uuid);
}