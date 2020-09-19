/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.helpers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

/**
 *
 * @author @JAEVCODE
 */
@Component
public class Util {

    public List<String> getErrorsMessages(BindingResult result) {
        return result.getFieldErrors().stream().map(
                fieldError
                -> fieldError.getField().concat(": ").concat(fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }
}
