/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaevcode.myunsplashclone.service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author @JAEVCODE
 */
@Service
public class UploadFileServiceImpl implements IUploadFileService {

    private final static String UPLOADS_FOLDER = "PHOTOSUPLOADS";

    @Override
    public Resource load(String filename) throws MalformedURLException {
        Path pathPhoto = getPath(filename);
        Resource resource = null;

        resource = new UrlResource(pathPhoto.toUri());
        if (!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("Error: can't load image: " + pathPhoto.toString());
        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file,String id) throws IOException {
        String uniqueFilename = id;
        Path rootPath = getPath(uniqueFilename);
        byte[] bytes = file.getBytes();
        Files.write(rootPath, bytes);
        return uniqueFilename;
    }

    @Override
    public boolean delete(String filename) {
        Path rootPath = getPath(filename);
        File file = rootPath.toFile();
        if (file.exists() && file.canRead()) {
            if (file.delete()) {
                return true;
            }
        }
        return false;
    }

    public Path getPath(String filename) {
        return Paths.get(UPLOADS_FOLDER).resolve(filename);
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(Paths.get(UPLOADS_FOLDER).toFile());
    }

    @Override
    public void createDirectory() {
        try {
            if (!Files.exists(Paths.get(UPLOADS_FOLDER))) {
                Files.createDirectory(Paths.get(UPLOADS_FOLDER));
            } else {
            }
        } catch (IOException ex) {
        }
    }

}
