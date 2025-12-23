package com.periodictable.controller;

import com.periodictable.entity.Element;
import com.periodictable.service.PeriodicTableElementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Periodic Table Controller", description = "Controller for returning elements of the periodic table.")
@RestController
@RequestMapping("/element")
public class PeriodicTableElementController {

  private final PeriodicTableElementService service;

  public PeriodicTableElementController(PeriodicTableElementService service) {
    this.service = service;
  }

  @Operation(summary = "Returns a partial list of elements.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", content = {@Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = Element.class))})
  })
  @GetMapping
  public Flux<Element> getElements(
      @RequestParam(name = "query", required = false) @Parameter(name = "query", description = "Filter for additional keywords", example = "gas") String query,
      @RequestParam(name = "max", required = false, defaultValue = "10") @Parameter(name = "max", description = "Max number of results per page. Default is 10") int pageSize,
      @RequestParam(name = "page", required = false, defaultValue = "0") @Parameter(name = "page", description = "Which page to return. Default is 0 (first page)") int pageIndex) {
    return service.getElements(query, pageIndex, pageSize);
  }

}
