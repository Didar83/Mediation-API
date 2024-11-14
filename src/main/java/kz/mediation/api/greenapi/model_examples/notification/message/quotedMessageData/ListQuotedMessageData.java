package kz.mediation.api.greenapi.model_examples.notification.message.quotedMessageData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListQuotedMessageData extends QuotedMessageData {
    private String contentText;
    private String title;
    private String footer;
    private String buttonText;
    private List<Section> sections;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Section {
        private String title;
        private List<Row> rows;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Row {
        private String rowId;
        private String title;
        private String description;
    }
}
