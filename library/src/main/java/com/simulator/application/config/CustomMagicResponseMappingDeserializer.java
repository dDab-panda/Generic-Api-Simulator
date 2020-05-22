package com.simulator.application.config;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.simulator.pojo.response.mapping.MagicResponse;

public final class CustomMagicResponseMappingDeserializer extends JsonDeserializer<MagicResponse> {

    @Override
    public MagicResponse deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        MagicResponse magicResponse = new MagicResponse();
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        magicResponse.setResponse(node.toString());
        return magicResponse;
    }
}