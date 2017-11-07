# putiojava

<aside class="warning">
This is a work in progress! Please contact me if you need anything or just make a pull request with changes.
  
  TODO: Define a nice way to get the user token.
  TODO: Allow upload of magnet links.
</aside>

This java client is designed to be hosted in a headless environment, which means direct downloads and uploads will probably be omitted.

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

