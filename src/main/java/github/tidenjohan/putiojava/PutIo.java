package github.tidenjohan.putiojava;

import com.fasterxml.jackson.core.type.TypeReference;
import github.tidenjohan.putiojava.dto.ListTransfersDto;
import github.tidenjohan.putiojava.dto.TransferResponseDto;
import github.tidenjohan.putiojava.dto.FileDto;
import github.tidenjohan.putiojava.dto.FileResponseDto;
import github.tidenjohan.putiojava.dto.ListFilesDto;
import github.tidenjohan.putiojava.dto.ListSubtitlesDto;
import github.tidenjohan.putiojava.dto.Mp4ConversionStatusDto;
import github.tidenjohan.putiojava.dto.Mp4ConversionStatusResponseDto;
import github.tidenjohan.putiojava.dto.ResponseDto;
import github.tidenjohan.putiojava.dto.TransferDto;
import github.tidenjohan.putiojava.internals.PutIoHttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PutIo {
    private static final String SCHEME = "https";
    private static final String DOMAIN = "put.io";


    public static final String API_BASE = SCHEME + "://api." + DOMAIN + "/v2";
    public static final String URL_FILES_LIST = API_BASE + "/files/list";
    public static final String URL_FILES_GET = API_BASE + "/files/%d";
    public static final String URL_FILES_DELETE = API_BASE + "/files/delete";
    public static final String URL_FILES_MOVE = API_BASE + "/files/move";
    public static final String URL_FILES_CONVERT = API_BASE + "/files/%d/mp4";
    public static final String URL_FILES_CREATE_FOLDER = API_BASE + "/files/create-folder";
    public static final String URL_FILES_RENAME = API_BASE + "/files/rename";
    public static final String URL_FILES_LIST_SUBTITLES = API_BASE + "/files/%d/subtitles";

    public static final String UPLOAD_BASE = SCHEME + "://upload." + DOMAIN;
    public static final String URL_FILES_UPLOAD = UPLOAD_BASE + "/files/upload";

    private static final String URL_TRANSFERS_ADD = API_BASE + "/transfers/add";
    private static final String URL_TRANSFERS_GET = API_BASE + "/transfers/%d";
    private static final String URL_TRANSFERS_LIST = API_BASE + "/transfers/list";
    private static final String URL_TRANSFERS_CLEAN = API_BASE + "/transfers/clean";
    private static final String URL_TRANSFERS_CANCEL = API_BASE + "/transfers/cancel";


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
        return putIoHttpClient.get(URL_FILES_LIST, token, new TypeReference<ListFilesDto>() {}, p("parent_id", parentId));
    }

    public FileDto getFile(long fileId) throws ApiException {
        FileResponseDto fileResponseDto = putIoHttpClient.get(String.format(URL_FILES_GET, fileId), token, new TypeReference<FileResponseDto>() {});
        return fileResponseDto.getFile();
    }

    public boolean deleteFiles(long... fileIds) throws ApiException {
        ResponseDto responseDto = putIoHttpClient.post(URL_FILES_DELETE, token, new TypeReference<ResponseDto>() {},
                p("file_ids", toCommaSeparatedString(fileIds)));
        return responseDto.getStatus().equals("OK");
    }
    
    public boolean deleteFiles(List<Long> fileIds) throws ApiException {
        ResponseDto responseDto = putIoHttpClient.post(URL_FILES_DELETE, token, new TypeReference<ResponseDto>() {},
                p("file_ids", toCommaSeparatedString(fileIds)));
        return responseDto.getStatus().equals("OK");
    }

    public boolean renameFile(long fileId, String name) throws ApiException {
        ResponseDto responseDto = putIoHttpClient.post(URL_FILES_RENAME, token, new TypeReference<ResponseDto>() {},
                p("file_id", fileId),
                p("name", name));
        return responseDto.getStatus().equals("OK");
    }

    public boolean moveFiles(long destinationParentId, long... fileIds) throws ApiException {
        ResponseDto responseDto = putIoHttpClient.post(URL_FILES_MOVE, token, new TypeReference<ResponseDto>() {},
                p("file_ids", toCommaSeparatedString(fileIds)),
                p("parent_id", destinationParentId));
        return responseDto.getStatus().equals("OK");
    }

    public boolean startMp4Conversion(long fileId) throws ApiException {
        ResponseDto responseDto = putIoHttpClient.post(String.format(URL_FILES_CONVERT, fileId), token, new TypeReference<ResponseDto>() {});
        return responseDto.getStatus().equals("OK");
    }

    public Mp4ConversionStatusDto getMp4ConversionStatus(long fileId) throws ApiException {
        Mp4ConversionStatusResponseDto responseDto = putIoHttpClient.get(String.format(URL_FILES_CONVERT, fileId), token, new TypeReference<Mp4ConversionStatusResponseDto>() {});
        return responseDto.getMp4();
    }

    public FileDto createFolder(long parentId, String name) throws ApiException {
        FileResponseDto fileResponseDto = putIoHttpClient.post(URL_FILES_CREATE_FOLDER, token, new TypeReference<FileResponseDto>() {},
                p("name", name),
                p("parent_id", parentId));
        return fileResponseDto
                .getFile();
    }

    public TransferDto addTransfer(String url, long destinationParentId, String callbackUrl) throws ApiException {
        return putIoHttpClient.post(URL_TRANSFERS_ADD, token, new TypeReference<TransferResponseDto>() {},
                p("url", url),
                p("save_parent_id", destinationParentId),
                p("callback_url", callbackUrl))
                .getTransfer();
    }


    public TransferDto addTransfer(String url, long parentId) throws ApiException {
        return addTransfer(url, parentId, null);
    }

    public TransferDto getTransfer(long transferId) throws ApiException {
        return putIoHttpClient.get(String.format(URL_TRANSFERS_GET, transferId), token, new TypeReference<TransferResponseDto>() {})
                .getTransfer();
    }


    public ListTransfersDto listTransfers() throws ApiException {
        return putIoHttpClient.get(URL_TRANSFERS_LIST, token, new TypeReference<ListTransfersDto>() {});
    }

    public ListSubtitlesDto listSubtitles(long fileId) throws ApiException {
        return putIoHttpClient.get(String.format(URL_FILES_LIST_SUBTITLES, fileId), token, new TypeReference<ListSubtitlesDto>() {});
    }

    private static String toCommaSeparatedString(long... ids) {
        List<String> collect = Arrays.stream(ids).mapToObj(Long::toString).collect(Collectors.toList());
        return String.join(",", collect);
    }

    private static String toCommaSeparatedString(Collection<Long> ids) {
        List<String> collect = ids.stream().map(Object::toString).collect(Collectors.toList());
        return String.join(",", collect);
    }

    private static NameValuePair p(String name, long value) {
        return p(name, ""+value);
    }

    private static NameValuePair p(String name, String value) {
        return new BasicNameValuePair(name, value);
    }

    public void cleanTransfers() throws ApiException {
        putIoHttpClient.post(URL_TRANSFERS_CLEAN, token, null);
    }

    public boolean cancelTransfer(long transferId) throws ApiException {
        ResponseDto responseDto = putIoHttpClient.post(URL_TRANSFERS_CANCEL, token, new TypeReference<ResponseDto>() {},
                p("transfer_ids", toCommaSeparatedString(transferId)));
        return responseDto.getStatus().equals("OK");
    }
}
