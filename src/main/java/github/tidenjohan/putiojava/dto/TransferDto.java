package github.tidenjohan.putiojava.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TransferDto {

    private final long uploaded;
    private final long estimatedTime;
    private final long peersGettingFromUs;
    private final boolean extract;
    private final double currentRatio;
    private final long size;
    private final long up_speed;
    private final long id;
    private final String source;
    private final Long subscriptionId;
    private final String statusMessage;
    private final String status;
    private final long downSpeed;
    private final long peersConnected;
    private final long downloaded;
    private final Long fileId;
    private final long peersSendingToUs;
    private final long percentDone;
    private final String trackerMessage;
    private final String name;
    private final String createdAt;
    private final String errorMessage;
    private final long saveParentId;

    @JsonCreator
    public TransferDto(
            @JsonProperty("uploaded") long uploaded,
            @JsonProperty("estimated_time") long estimatedTime,
            @JsonProperty("peers_getting_from_us") long peersGettingFromUs,
            @JsonProperty("extract") boolean extract,
            @JsonProperty("current_ratio") double currentRatio,
            @JsonProperty("size") long size,
            @JsonProperty("up_speed") long up_speed,
            @JsonProperty("id") long id,
            @JsonProperty("source") String source,
            @JsonProperty("subscription_id") Long subscriptionId,
            @JsonProperty("status_message") String statusMessage,
            @JsonProperty("status") String status,
            @JsonProperty("down_speed") long downSpeed,
            @JsonProperty("peers_connected") long peersConnected,
            @JsonProperty("downloaded") long downloaded,
            @JsonProperty("file_id") Long fileId,
            @JsonProperty("peers_sending_to_us") long peersSendingToUs,
            @JsonProperty("percent_done") long percentDone,
            @JsonProperty("tracker_message") String trackerMessage,
            @JsonProperty("name") String name,
            @JsonProperty("created_at") String createdAt,
            @JsonProperty("error_message") String errorMessage,
            @JsonProperty("save_parent_id") long saveParentId) {
        this.uploaded = uploaded;
        this.estimatedTime = estimatedTime;
        this.peersGettingFromUs = peersGettingFromUs;
        this.extract = extract;
        this.currentRatio = currentRatio;
        this.size = size;
        this.up_speed = up_speed;
        this.id = id;
        this.source = source;
        this.subscriptionId = subscriptionId;
        this.statusMessage = statusMessage;
        this.status = status;
        this.downSpeed = downSpeed;
        this.peersConnected = peersConnected;
        this.downloaded = downloaded;
        this.fileId = fileId;
        this.peersSendingToUs = peersSendingToUs;
        this.percentDone = percentDone;
        this.trackerMessage = trackerMessage;
        this.name = name;
        this.createdAt = createdAt;
        this.errorMessage = errorMessage;
        this.saveParentId = saveParentId;
    }


    public long getUploaded() {
        return uploaded;
    }

    public long getEstimatedTime() {
        return estimatedTime;
    }

    public long getPeersGettingFromUs() {
        return peersGettingFromUs;
    }

    public boolean isExtract() {
        return extract;
    }

    public double getCurrentRatio() {
        return currentRatio;
    }

    public long getSize() {
        return size;
    }

    public long getUp_speed() {
        return up_speed;
    }

    public long getId() {
        return id;
    }

    public String getSource() {
        return source;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatus() {
        return status;
    }

    public long getDownSpeed() {
        return downSpeed;
    }

    public long getPeersConnected() {
        return peersConnected;
    }

    public long getDownloaded() {
        return downloaded;
    }

    public Long getFileId() {
        return fileId;
    }

    public long getPeersSendingToUs() {
        return peersSendingToUs;
    }

    public long getPercentDone() {
        return percentDone;
    }

    public String getTrackerMessage() {
        return trackerMessage;
    }

    public String getName() {
        return name;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public long getSaveParentId() {
        return saveParentId;
    }
}
