package kz.mediation.api.greenapi.model_examples.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OutgoingMessage extends Outgoing {
    private final String message;
    private final Boolean linkPreview;

    private OutgoingMessage(Builder builder) {
        super(builder.chatId, builder.quotedMessageId);
        this.message = builder.message;
        this.linkPreview = builder.linkPreview;
    }

    public static class Builder {
        private String message;
        private Boolean linkPreview;
        private String chatId;
        private String quotedMessageId;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder linkPreview(Boolean linkPreview) {
            this.linkPreview = linkPreview;
            return this;
        }

        public Builder chatId(String chatId) {
            this.chatId = chatId;
            return this;
        }

        public Builder quotedMessageId(String quotedMessageId) {
            this.quotedMessageId = quotedMessageId;
            return this;
        }

        public OutgoingMessage build() {
            return new OutgoingMessage(this);
        }
    }
}
