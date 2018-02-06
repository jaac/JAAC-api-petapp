package es.jaac.petlost.http;

public class HttpRequestBuilder {

    private HttpRequest httpRequest;

    public HttpRequestBuilder() {
        httpRequest = new HttpRequest();
    }

    public HttpRequestBuilder method(HttpMethod method) {
        httpRequest.setMethod(method);
        return this;
    }

    public HttpRequestBuilder path(String path) {
        httpRequest.setPath(httpRequest.getPath() + path);
        return this;
    }

    public HttpRequestBuilder expandPath(String value) {
        String path = httpRequest.getPath();
        path = path.substring(0, path.indexOf('{')) + value + path.substring(path.indexOf('}') + 1);
        httpRequest.setPath(path);
        return this;
    }

    public HttpRequestBuilder param(String key, String value) {
        httpRequest.addQueryParam(key, value);
        return this;
    }

    public HttpRequestBuilder headerParam(String key, String value) {
        httpRequest.addHeaderParam(key, value);
        return this;
    }

    public HttpRequestBuilder body(String body) {
        httpRequest.setBody(body);
        return this;
    }

    public HttpRequest build() {
        return httpRequest;
    }

}