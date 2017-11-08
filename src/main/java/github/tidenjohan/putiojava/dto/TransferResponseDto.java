package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferResponseDto {

    private final String status;
    private final TransferDto transfer;

    @JsonCreator
    public TransferResponseDto(
            @JsonProperty("status") String status,
            @JsonProperty("transfer") TransferDto transfer) {
        this.status = status;
        this.transfer = transfer;
    }

    public String getStatus() {
        return status;
    }

    public TransferDto getTransfer() {
        return transfer;
    }
}
