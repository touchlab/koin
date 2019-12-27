/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.koin.dsl

import org.koin.core.definition.*
import org.koin.core.qualifier.Qualifier
import org.koin.core.scope.ScopeDefinition

/**
 * DSL Scope Definition
 */
class ScopeDSL(val scopeDefinition: ScopeDefinition) {

    inline fun <reified T> scoped(
            qualifier: Qualifier? = null,
            override: Boolean = false,
            threadScope: ThreadScope = ThreadScope.Main,
            noinline definition: Definition<T>
    ): BeanDefinition<T> {
        return Definitions.saveSingle(
            qualifier,
            definition,
            scopeDefinition,
            Options(isCreatedAtStart = false, override = override),
                threadScope
        )
    }

    inline fun <reified T> factory(
            qualifier: Qualifier? = null,
            override: Boolean = false,
            threadScope: ThreadScope = ThreadScope.Main,
            noinline definition: Definition<T>
    ): BeanDefinition<T> {
        return Definitions.saveFactory(
            qualifier,
            definition,
            scopeDefinition,
            Options(isCreatedAtStart = false, override = override),
                threadScope
        )
    }
}