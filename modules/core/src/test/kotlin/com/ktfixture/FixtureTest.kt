package com.ktfixture

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FixtureTest {

    @Test
    fun `should create a single defination with a unique name and class`() {
        val definition = Fixture.prepare<Mock> {
            template("valid", {})
        }

        assertThat(definition.hasTemplate("valid"))

    }
}

data class Mock(val name: String, val age: Int)