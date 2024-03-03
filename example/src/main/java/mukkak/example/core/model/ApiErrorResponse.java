package mukkak.example.core.model;

public record ApiErrorResponse(String errorCode, String errorMessage) {
    /*
    * 	"type": "about:blank",
	"title": "Not Found",
	"status": 404,
	"detail": "No static resource xyz.",
	"instance": "/xyz"
    * */
}
