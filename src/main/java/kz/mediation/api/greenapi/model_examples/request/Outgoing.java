package kz.mediation.api.greenapi.model_examples.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@RequiredArgsConstructor
@SuperBuilder
public abstract class Outgoing {
    private final String chatId;
    private final String quotedMessageId;
}
