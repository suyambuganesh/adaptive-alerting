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
package com.expedia.adaptivealerting.anomdetect.outlier;

import com.expedia.adaptivealerting.anomdetect.Detector;
import com.expedia.adaptivealerting.anomdetect.outlier.aggregator.Aggregator;
import com.expedia.adaptivealerting.anomdetect.outlier.aggregator.PassThroughAggregator;
import lombok.Getter;

import java.util.UUID;

import static com.expedia.adaptivealerting.anomdetect.util.AssertUtil.notNull;

public abstract class AbstractOutlierDetector implements Detector {

    @Getter
    private UUID uuid;

    @Getter
    private Aggregator aggregator;

    public AbstractOutlierDetector(UUID uuid) {
        this(uuid, new PassThroughAggregator());
    }

    public AbstractOutlierDetector(UUID uuid, Aggregator aggregator) {
        notNull(uuid, "uuid can't be null");
        notNull(aggregator, "aggregator can't be null");

        this.uuid = uuid;
        this.aggregator = aggregator;
    }
}
