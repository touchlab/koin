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
package org.koin.androidx.viewmodel.ext.android

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.ViewModelStateDefinition
import org.koin.androidx.viewmodel.ViewModelStoreDefinition
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import kotlin.reflect.KClass

/**
 * Fragment extension to help for Viewmodel
 *
 * @author Arnaud Giuliani
 */

/**
 * Lazy getByClass a viewModel instance shared with Activity
 *
 * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
 * @param from - ViewModelStoreOwner that will store the viewModel instance. Examples: "parentFragment", "activity". Default: "activity"
 * @param defaultArguments - Default arguments for SavedStateHandle if useState = true
 * @param parameters - parameters to pass to the BeanDefinition
 */
inline fun <reified T : ViewModel> Fragment.sharedViewModel(
    qualifier: Qualifier? = null,
    noinline storeDefinition: ViewModelStoreDefinition = { requireActivity().viewModelStore },
    noinline defaultArguments: ViewModelStateDefinition? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> =
    lazy { getSharedViewModel<T>(qualifier, storeDefinition, defaultArguments, parameters) }

/**
 * Lazy getByClass a viewModel instance shared with Activity
 *
 * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
 * @param from - ViewModelStoreOwner that will store the viewModel instance. Examples: "parentFragment", "activity". Default: "activity"
 * @param defaultArguments - Default arguments for SavedStateHandle if useState = true
 * @param parameters - parameters to pass to the BeanDefinition
 * @param clazz
 */
fun <T : ViewModel> Fragment.sharedViewModel(
    clazz: KClass<T>,
    qualifier: Qualifier? = null,
    storeDefinition: ViewModelStoreDefinition = { requireActivity().viewModelStore },
    defaultArguments: ViewModelStateDefinition? = null,
    parameters: ParametersDefinition? = null
): Lazy<T> =
    lazy { getSharedViewModel(clazz, qualifier, storeDefinition, defaultArguments, parameters) }

/**
 * Get a shared viewModel instance from underlying Activity
 *
 * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
 * @param from - ViewModelStoreOwner that will store the viewModel instance. Examples: ("parentFragment", "activity"). Default: "activity"
 * @param defaultArguments - Default arguments for SavedStateHandle if useState = true
 * @param parameters - parameters to pass to the BeanDefinition
 */
inline fun <reified T : ViewModel> Fragment.getSharedViewModel(
    qualifier: Qualifier? = null,
    noinline storeDefinition: ViewModelStoreDefinition = { requireActivity().viewModelStore },
    noinline defaultArguments: ViewModelStateDefinition? = null,
    noinline parameters: ParametersDefinition? = null
): T {
    return getSharedViewModel(T::class, qualifier, storeDefinition, defaultArguments, parameters)
}

/**
 * Get a shared viewModel instance from underlying Activity
 *
 * @param qualifier - Koin BeanDefinition qualifier (if have several ViewModel beanDefinition of the same type)
 * @param from - ViewModelStoreOwner that will store the viewModel instance. Examples: ("parentFragment", "activity"). Default: "activity"
 * @param defaultArguments - Default arguments for SavedStateHandle if useState = true
 * defaultArguments: Bundle? = null,
 * @param parameters - parameters to pass to the BeanDefinition
 * @param clazz
 */
fun <T : ViewModel> Fragment.getSharedViewModel(
    clazz: KClass<T>,
    qualifier: Qualifier? = null,
    storeDefinition: ViewModelStoreDefinition = { requireActivity().viewModelStore },
    defaultArguments: ViewModelStateDefinition? = null,
    parameters: ParametersDefinition? = null
): T {
    return getViewModel(
        clazz,
        qualifier,
        storeDefinition,
        defaultArguments,
        parameters
    )
}