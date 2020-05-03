package com.henu.reservoir.controller;

import com.henu.reservoir.service.FittingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class settingController {
    private FittingService fittingService;

    @Autowired
    private void setServices(
            FittingService fittingService
    ) {
        this.fittingService = fittingService;
    }

    @GetMapping("/api/setting/pauseFitting")
    @ResponseBody
    public void pauseFitting(HttpSession session, String method) {
        //仅实现了会话级别的设置
        if (!"".equals(method)) {
            session.setAttribute("pauseFitting", true);
        } else {
            session.setAttribute("pauseFitting", null);
        }
    }

    @GetMapping("/api/setting/getPauseFittingStatus")
    @ResponseBody
    public String pauseFitting(HttpSession session) {
        if (session.getAttribute("pauseFitting") != null) {
            return "true";
        }
        else {
            return "false";
        }
    }

    @GetMapping("/api/setting/fitLevelNow")
    @ResponseBody
    public void fitLevelNow(HttpSession session) {
        if (session.getAttribute("reservoirId") != null) {
            int rid = (Integer) session.getAttribute("reservoirId");
            fittingService.fitMeasureLevel(rid);
            fittingService.fitRadarLevel(rid);
        }
    }

    @GetMapping("/api/setting/fitAreaNow")
    @ResponseBody
    public void fitAreaNow(HttpSession session) {
        if (session.getAttribute("reservoirId") != null) {
            int rid = (Integer) session.getAttribute("reservoirId");
            //fittingService.fitMeasuresLevelSarAndOpticalArea(rid);
            fittingService.fitRadarLevelSarAndOpticalArea(rid);
            fittingService.fitRadarLevelOpticalArea(rid);
            fittingService.fitRadarLevelSarArea(rid);
        }
    }

    @GetMapping("/api/setting/fitAllNow")
    @ResponseBody
    public void fitAllNow(HttpSession session) {
        if (session.getAttribute("reservoirId") != null) {
            int rid = (Integer) session.getAttribute("reservoirId");
            fittingService.fitMeasureLevel(rid);
            fittingService.fitRadarLevel(rid);
            //fittingService.fitMeasuresLevelSarAndOpticalArea(rid);
            fittingService.fitRadarLevelSarAndOpticalArea(rid);
            fittingService.fitRadarLevelOpticalArea(rid);
            fittingService.fitRadarLevelSarArea(rid);
        }
    }
}
