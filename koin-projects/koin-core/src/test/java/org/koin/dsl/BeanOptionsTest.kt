package org.koin.dsl

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.koin.Simple
import org.koin.test.getBeanDefinition

class BeanOptionsTest {

    @Test
    fun `definition created at start`() {
        val app = koinApplication {
            modules(
                module {
                    single(createdAtStart = true) { Simple.ComponentA() }
                    single { Simple.ComponentB(get()) }
                }
            )
        }

        val defA = app.getBeanDefinition(Simple.ComponentA::class) ?: error("no definition found")
        assertTrue(defA.options.isCreatedAtStart)

        val defB = app.getBeanDefinition(Simple.ComponentB::class) ?: error("no definition found")
        assertFalse(defB.options.isCreatedAtStart)
    }

    @Test
    fun `definition override`() {
        val app = koinApplication {
            modules(
                module {
                    single { Simple.ComponentA() }
                    single(override = true) { Simple.ComponentB(get()) }
                }
            )
        }

        val defA = app.getBeanDefinition(Simple.ComponentA::class) ?: error("no definition found")
        assertFalse(defA.options.override)

        val defB = app.getBeanDefinition(Simple.ComponentB::class) ?: error("no definition found")
        assertTrue(defB.options.override)
    }
}