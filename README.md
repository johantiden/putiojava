# putiojava

## Setup

1. Clone the repository.
2. Build with maven.
3. Add a maven dependency on the version you just built.

## Usage

// Apache httpClient
CloseableHttpClient httpClient = HttpClientBuilder.create()
               .setRedirectStrategy(new DefaultRedirectStrategy())
               .build();

// jackson-databind JSON parser
ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


PutIoHttpClient putIoHttpClient = new PutIoHttpClient(httpClient, objectMapper);

PutIoToken userToken = new PutIoToken("XXXXX"); // Insert user token here.
PutIo putIo = new PutIo(putIoHttpClient, userToken);


ListFilesDto listFilesDto = putIio.getFiles();


