package com.henu.reservoir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
public class UploadTestController {
    @GetMapping("test/file")
    public String file(Model model){
        model.addAttribute("filename", "file.getName()");
        model.addAttribute("filesize", "file.getSize()");
        return "fileTest";
    }

    @GetMapping("test/getId")
    @ResponseBody
    public String getId(HttpSession session) {
        String id = session.getAttribute("globalReservoirId") != null ?
                session.getAttribute("globalReservoirId").toString() :
                "null";
        return id;
    }

    @RequestMapping("test/fileUpload")
    public String fileUpload(@RequestParam("file")MultipartFile file, Model model){
        model.addAttribute("filename", file.toString());
        model.addAttribute("filesize", file.getSize());
        return "fileTest";
    }
}
