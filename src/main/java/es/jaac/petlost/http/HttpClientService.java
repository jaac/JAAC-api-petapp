package es.jaac.petlost.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpClientService {

    public HttpResponse httpRequest(HttpRequest request) {
        Logger logger = LogManager.getLogger(this.getClass().getName());
        System.out.println(request.toString());
        logger.info(request.toString());
        HttpResponse response = new Server().submit(request);
        logger.info(response);
        System.out.println(response);
        System.out.println("---------------------------------------ooo----------------------------------------");
        logger.info("---------------------------------------ooo----------------------------------------");
        if (response.getStatus().isError()) {
            throw new HttpException(response.getStatus().toString());
        }
        return response;
    }
}
