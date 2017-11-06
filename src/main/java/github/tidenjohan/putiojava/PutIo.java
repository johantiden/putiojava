package github.tidenjohan.putiojava;

import com.fasterxml.jackson.core.type.TypeReference;
import github.tidenjohan.putiojava.dto.FileDto;
import github.tidenjohan.putiojava.entity.File;
import github.tidenjohan.putiojava.internals.PutIoHttpClient;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public List<File> getFiles() throws ApiException {
        return getFiles(0);
    }

    public List<File> getFiles(long parentId) throws ApiException {
        List<FileDto> fileDtos = putIoHttpClient.get(LIST_FILES, token, new TypeReference<List<FileDto>>() {});
        return toFileEntities(fileDtos);
    }

    private static List<File> toFileEntities(List<FileDto> fileDtos) {
        return fileDtos.stream()
                .map(PutIo::toFileEntity)
                .collect(Collectors.toList());
    }

    private static File toFileEntity(FileDto fileDto) {
        return new File(fileDto.getId());
    }

}
