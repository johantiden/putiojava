package github.tidenjohan.putiojava;

import com.fasterxml.jackson.core.type.TypeReference;
import github.tidenjohan.putiojava.dto.ListFilesDto;
import github.tidenjohan.putiojava.internals.PutIoHttpClient;

import java.util.Objects;

public class PutIo {
    private static final String SCHEME = "https";
    private static final String DOMAIN = "put.io";

    public static final String API_BASE = SCHEME + "://api." + DOMAIN + "/v2";
    public static final String UPLOAD_BASE = SCHEME + "://upload." + DOMAIN;

    public static final String LIST_FILES = API_BASE + "/files/list";

    private final PutIoToken token;
    private final PutIoHttpClient putIoHttpClient;


    public PutIo(PutIoHttpClient putIoHttpClient, PutIoToken token) {
        this.putIoHttpClient = Objects.requireNonNull(putIoHttpClient);
        this.token = Objects.requireNonNull(token);
    }

    /**
     * Gets all files in the user's root folder.
     * @throws ApiException
     */
    public ListFilesDto getFiles() throws ApiException {
        return getFiles(0);
    }

    public ListFilesDto getFiles(long parentId) throws ApiException {
        ListFilesDto listFilesDto = putIoHttpClient.get(LIST_FILES, token, new TypeReference<ListFilesDto>() {});
        return listFilesDto;
    }
}
