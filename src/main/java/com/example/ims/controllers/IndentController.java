package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.services.IndentService;
import com.example.ims.views.IndentLineItemView;
import com.example.ims.views.IndentView;
import com.example.ims.views.ReturnIndentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ImsConstants.ApiPaths.INDENT_ROOT)
public class IndentController {
    @Autowired
    IndentService indentService;
    @GetMapping
    public ResponseEntity getListOfIndents(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Indents
        List<IndentView> indentViews;
        try{
            indentViews = indentService.getListOfIndents(pageNo, pageSize, searchParam);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(indentViews);
    }

    @GetMapping(ImsConstants.ApiPaths.INDENT_ID)
    public ResponseEntity getIndentForId(@PathVariable int indentId){
        //get indent for the given id
        IndentView indentView;
        try{
            indentView = indentService.getIndentForId(indentId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(indentView);
    }

    @PostMapping
    public ResponseEntity createIndent(@RequestBody IndentView indentView){
        //Create a new Indent
        IndentView indent;
        try{
            indent = indentService.createIndent(indentView);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(indent);

    }

    @PutMapping(ImsConstants.ApiPaths.INDENT_ID)
    public ResponseEntity updateIndent(@PathVariable int indentId, @RequestBody IndentView indentView){
        //Update an Indent
        IndentView indent;
        try{
            indent = indentService.updateIndent(indentView,indentId);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(indent);
    }

    @DeleteMapping(ImsConstants.ApiPaths.INDENT_ID)
    public ResponseEntity deleteIndent(@PathVariable int indentId){
        //ToDo delete an Indent
        return ResponseEntity.ok().body("OK");
    }



    @GetMapping(ImsConstants.ApiPaths.RETURN_INDENT_ROOT)
    public ResponseEntity getListOfReturnIndents(@RequestParam int pageNo, @RequestParam int pageSize,
                                                 @RequestParam String searchParam){
        //get List of Return Indents
        List<ReturnIndentView> returnIndentViews;
        try {
            returnIndentViews = indentService.getListOfReturnIndents(pageNo, pageSize, searchParam);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(returnIndentViews);
    }

    @GetMapping(ImsConstants.ApiPaths.RETURN_INDENT_ID)
    public ResponseEntity getReturnIndentForId(@PathVariable int indentId){
        //get indent for the given id
        ReturnIndentView returnIndentView;
        try {
            returnIndentView = indentService.getReturnIndentForId(indentId);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(returnIndentView);
    }

    @PostMapping(ImsConstants.ApiPaths.RETURN_INDENT_ROOT)
    public ResponseEntity createReturnIndent(@RequestBody ReturnIndentView returnIndentView){
        //Create a new Return Indent
        ReturnIndentView returnIndentViewObj;
        try {
            returnIndentViewObj = indentService.createReturnIndent(returnIndentView);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
        return ResponseEntity.ok().body(returnIndentViewObj);
    }



}
