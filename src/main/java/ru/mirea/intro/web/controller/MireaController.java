package ru.mirea.intro.web.controller;

import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.intro.mapper.RequestMapper;
import ru.mirea.intro.service.TestService;
import ru.mirea.intro.service.model.Request;
import ru.mirea.intro.web.to.Meta;
import ru.mirea.intro.web.to.RequestDto;
import ru.mirea.intro.web.to.Response;


@RestController
@Api(tags = "MireaController API",description = "Description of my MireaController RESTfull API ")
@RequestMapping("api/mirea")
public class MireaController {
    private final TestService testService;

    public MireaController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/post-method")
    @ApiOperation(value = "add request and all books in data base")
    @ApiParam(name = "requestDto", value = "request model with list of book models")
    public ResponseEntity<Response<RequestDto>> postMethod(@RequestBody RequestDto requestDto) {
        try {
            Request request = RequestMapper.REQUEST_MAPPER.requestDTOToRequest(requestDto);
            Request testServiceResponse = testService.testServicePostMethod(request);
            RequestDto testServiceResponseDTO = RequestMapper.REQUEST_MAPPER.requestToRequestDto(testServiceResponse);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get-method")
    @ApiOperation(value = "get request and all books of that request from data base by id")
    @ApiParam(name = "id", value = "id of request in data base")
    public ResponseEntity<Response<RequestDto>> getMethod(@RequestParam Long id) {
        try {
            Request request = testService.testServiceGetMethod(id);
            RequestDto requestDto = RequestMapper.REQUEST_MAPPER.requestToRequestDto(request);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), requestDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/put-method")
    @ApiOperation(value = "update request and inner books rows in database",
    notes = "request id must exist in database \n" +
            "method delete all books from old request, and add all books from new request")
    @ApiParam(name = "requestDto", value = "request model with list of book models to update")
    public ResponseEntity<Response<RequestDto>> putMethod(@RequestBody RequestDto requestDto) {
        try {
            Request request = RequestMapper.REQUEST_MAPPER.requestDTOToRequest(requestDto);
            Request testServiceResponse = testService.testServicePutMethod(request);
            RequestDto testServiceResponseDTO = RequestMapper.REQUEST_MAPPER.requestToRequestDto(testServiceResponse);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponseDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete-method")
    @ApiOperation(value = "delete request and all books from this request from data base by request id")
    @ApiParam(name = "id", value = "id of request in data base")
    public ResponseEntity<Response<String>> deleteMethod(@RequestParam Long id) {
        try {
            String testServiceResponse = testService.testServiceDeleteMethod(id);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

}
