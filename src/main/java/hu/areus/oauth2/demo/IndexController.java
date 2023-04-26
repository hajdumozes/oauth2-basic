package hu.areus.oauth2.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping
@Tag(name = "Demo", description = "Demo OAUTH2 Backend")
public class IndexController {

    @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(operationId = "Hello",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain"))
            })
    public String hello(Principal principal) {
        return principal.getName() + " says hello";
    }
}
