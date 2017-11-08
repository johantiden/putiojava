package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ListTransfersDto {
    private final String status;
    private final List<TransferDto> transfers;

    @JsonCreator
    public ListTransfersDto(
            @JsonProperty("status") String status,
            @JsonProperty("transfers") List<TransferDto> transfers) {
        this.status = status;
        this.transfers = transfers;
    }

    public String getStatus() {
        return status;
    }

    public List<TransferDto> getTransfers() {
        return transfers;
    }
}
