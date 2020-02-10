package com.example.ims.controllers;

import com.example.ims.constants.ImsConstants;
import com.example.ims.views.IndentLineItemView;
import com.example.ims.views.IndentView;
import com.example.ims.views.ReturnIndentView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(ImsConstants.ApiPaths.INDENT_ROOT)
public class IndentController {

    @GetMapping
    public ResponseEntity getListOfIndents(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Indents
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.INDENT_ID)
    public ResponseEntity getIndentForId(@PathVariable int indentId){
        //get indent for the given id
        IndentLineItemView lineItem = new IndentLineItemView();
        List<IndentLineItemView> lineItemList = new ArrayList<>();
        lineItemList.add(lineItem);
        IndentView indentView = new IndentView();
        indentView.setIndentLineItemViewList(lineItemList);
        return ResponseEntity.ok().body(indentView);
    }

    @PostMapping
    public ResponseEntity createIndent(@RequestBody IndentView indentView){
        //Create a new Indent
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping(ImsConstants.ApiPaths.INDENT_ID)
    public ResponseEntity updateIndent(@PathVariable int indentId, @RequestBody IndentView indentView){
        //Create a new Indent
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping(ImsConstants.ApiPaths.INDENT_ID)
    public ResponseEntity deleteIndent(@PathVariable int indentId){
        //Create a new Indent
        return ResponseEntity.ok().body("OK");
    }



    @GetMapping(ImsConstants.ApiPaths.RETURN_INDENT_ROOT)
    public ResponseEntity getListOfReturnIndents(@RequestParam int pageNo, @RequestParam int pageSize, @RequestParam String searchParam){
        //get List of Return Indents
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping(ImsConstants.ApiPaths.RETURN_INDENT_ID)
    public ResponseEntity getReturnIndentForId(@PathVariable int indentId){
        //get indent for the given id
        IndentLineItemView lineItem = new IndentLineItemView();
        List<IndentLineItemView> lineItemList = new ArrayList<>();
        lineItemList.add(lineItem);
        ReturnIndentView indentView = new ReturnIndentView();
        indentView.setIndentLineItemViewList(lineItemList);
        return ResponseEntity.ok().body(indentView);
    }

    @PostMapping(ImsConstants.ApiPaths.RETURN_INDENT_ROOT)
    public ResponseEntity createReturnIndent(@RequestBody ReturnIndentView returnIndentView){
        //Create a new Return Indent
        return ResponseEntity.ok().body("OK");
    }



}
