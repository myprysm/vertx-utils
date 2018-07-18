/*
 * Copyright 2018 the original author or the original authors
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

package fr.myprysm.vertx.elasticsearch.support;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.join.ParentJoinPlugin;
import org.elasticsearch.plugins.spi.NamedXContentProvider;
import org.elasticsearch.search.SearchModule;
import org.elasticsearch.search.aggregations.matrix.MatrixAggregationPlugin;

import java.util.Arrays;
import java.util.List;

public class SearchBuildersNamedXContentProvider implements NamedXContentProvider {
    @Override
    public List<NamedXContentRegistry.Entry> getNamedXContentParsers() {
        return new SearchModule(
                Settings.EMPTY,
                false,
                Arrays.asList(new MatrixAggregationPlugin(), new ParentJoinPlugin())
        ).getNamedXContents();
    }
}
