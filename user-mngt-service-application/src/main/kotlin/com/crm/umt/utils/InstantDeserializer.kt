package com.crm.umt.utils

import java.time.Instant

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer

class InstantDeserializer : StdDeserializer<Instant>(Instant::class.java) {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Instant {
        return Instant.parse(p.text)
    }
}