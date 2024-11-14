package kz.mediation.api.mediation_executor.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData.*;

import java.io.IOException;

public class QuotedMessageDeserializer extends StdDeserializer<QuotedMessageData> {

    public QuotedMessageDeserializer() {
        this(null);
    }

    public QuotedMessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public QuotedMessageData deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.has("typeMessage")) {
            switch (node.get("typeMessage").toString()) {
                case "buttonMessage" -> {
                    return ctxt.readValue(jp, ButtonQuotedMessageData.class);
                }
                case "contactMessage" -> {
                    return ctxt.readValue(jp, ContactQuotedMessageData.class);
                }
                case "contactsArrayMessage" -> {
                    return ctxt.readValue(jp, ContactsArrayQuotedMessageData.class);
                }
                case "extendedTextMessage" -> {
                    return ctxt.readValue(jp, ExtendedTextQuotedMessageData.class);
                }
                case "imageMessage", "videoMessage", "documentMessage", "audioMessage" -> {
                    return ctxt.readValue(jp, FileQuotedMessageData.class);
                }
                case "groupInviteMessage" -> {
                    return ctxt.readValue(jp, GroupInviteQuotedMessageData.class);
                }
                case "listMessage" -> {
                    return ctxt.readValue(jp, ListQuotedMessageData.class);
                }
                case "locationMessage" -> {
                    return ctxt.readValue(jp, LocationQuotedMessageData.class);
                }
                case "pollMessage" -> {
                    return ctxt.readValue(jp, PollQuotedMessageData.class);
                }
                case "reactionMessage" -> {
                    return ctxt.readValue(jp, ReactionQuotedMessageData.class);
                }
                case "stickerMessage" -> {
                    return ctxt.readValue(jp, StickerQuotedMessageData.class);
                }
                case "templateMessage" -> {
                    return ctxt.readValue(jp, TemplateQuotedMessageData.class);
                }
                case "textMessage" -> {
                    return ctxt.readValue(jp, TextQuotedMessageData.class);
                }
                case "templateButtonsReplyMessage" -> {
                    return ctxt.readValue(jp, TemplateButtonReplyQuotedMessageData.class);
                }
            }
        }
        return new EmptyQuotedMessageData();
    }
}
