package com.example.product.api.deserializer;

import com.example.product.api.Now;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import java.io.IOException;

/**
 * custom json deserializer for now price.
 * "now" field's value can be double or object which contains "to" and "from" values
 *
 * @author birolg, 3/17/2019.
 */
public class NowDeserializer extends JsonDeserializer<Now> {

    @Override
    public Now deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
        Now now = new Now();

        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);

        if (node.getNodeType() == JsonNodeType.STRING) {
            now.setNow(node.asDouble());
        } else if (node.getNodeType() == JsonNodeType.OBJECT) {
            now.setFrom(node.get("from").asDouble());
            now.setTo(node.get("to").asDouble());
        }

        return now;
    }

}
