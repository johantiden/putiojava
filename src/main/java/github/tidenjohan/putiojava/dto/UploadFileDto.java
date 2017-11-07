package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadFileDto {
    private final String file;
    private final TransferDto transfer;

    @JsonCreator
    public UploadFileDto(
            @JsonProperty("file") String file,
            @JsonProperty("transfer") TransferDto transfer) {
        this.file = file;
        this.transfer = transfer;
    }

    public String getFile() {
        return file;
    }

    public TransferDto getTransfer() {
        return transfer;
    }

    public static class TransferDto {

    }
}
