# putiojava

## Setup

1. Clone the repository.
2. Build with maven.
3. Add a maven dependency on the version you just built.

## Usage
### Dependencies
#### Apache httpClient
CloseableHttpClient httpClient = HttpClientBuilder.create()
               .setRedirectStrategy(new DefaultRedirectStrategy())
               .build();


#### jackson-databind JSON parser
ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);



### In action
PutIoToken userToken = new PutIoToken("XXXXX"); // Insert user token here.

PutIo putIo = new PutIo(new PutIoHttpClient(httpClient, objectMapper), userToken);


ListFilesDto listFilesDto = putIio.getFiles();

FileDto file = putIo.getFile(1234);

putIo.deleteFile(1234);

