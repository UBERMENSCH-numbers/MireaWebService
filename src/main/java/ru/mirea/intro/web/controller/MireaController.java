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
@Api(value = "MireaController API",description = "Description of my MireaController RESTfull API ")
@RequestMapping("api/mirea")
public class MireaController {
    private final TestService testService;

    public MireaController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/post-method")
    @ApiOperation(value = "add request and all books in data base")
    @ApiImplicitParam(name = "requestDto", value = "request model with list of book models")
    public ResponseEntity<Response<String>> postMethod(@RequestBody RequestDto requestDto) {
        try {
            Request request = RequestMapper.REQUEST_MAPPER.requestDTOToRequest(requestDto);
            String testServiceResponse = testService.testServicePostMethod(request);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/get-method")
    @ApiOperation(value = "get request and all books of that request from data base by id")
    @ApiImplicitParam(name = "id", value = "id of request in data base")
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
    @ApiImplicitParam(name = "requestDto", value = "request model with list of book models to update")
    public ResponseEntity<Response<String>> putMethod(@RequestBody RequestDto requestDto) {
        try {
            Request request = RequestMapper.REQUEST_MAPPER.requestDTOToRequest(requestDto);
            String testServiceResponse = testService.testServicePutMethod(request);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/delete-method")
    @ApiOperation(value = "delete request and all books from this request from data base by request id")
    @ApiImplicitParam(name = "id", value = "id of request in data base")
    public ResponseEntity<Response<String>> deleteMethod(@RequestParam Long id) {
        try {
            String testServiceResponse = testService.testServiceDeleteMethod(id);
            return new ResponseEntity<>(new Response<>(new Meta(0, "All good!"), testServiceResponse), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Response<>(new Meta(1, e.toString()), null), HttpStatus.CONFLICT);
        }
    }

}
