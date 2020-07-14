package movie;

import java.net.URI;
import java.net.http.*;
import java.util.concurrent.CompletableFuture;

public class Api_call {

	public static CompletableFuture<String> api_thing() {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(str))
				.build();
	}
	
}
