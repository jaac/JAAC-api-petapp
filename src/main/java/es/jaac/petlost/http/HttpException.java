package es.jaac.petlost.http;

public class HttpException extends RuntimeException {
    private static final long serialVersionUID = 7271728903247259709L;

    public HttpException(String msg) {
        super(msg);
    }

}
