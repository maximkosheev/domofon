package ru.monsterdev.domofon.controllers;

import com.jcabi.manifests.Manifests;
import com.jcabi.manifests.ServletMfs;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/home")
public class HomeController {

  @Autowired
  private ServletContext servletContext;

  @GetMapping
  public String home(Model model) {
    return "forward:/index.html";
  }

  @GetMapping("/version")
  public ResponseEntity getVersion() {
    Map<String, String> result = new HashMap<>();
    try {
      Manifests.DEFAULT.append(new ServletMfs(servletContext));
      result.put("version", Manifests.read("Current-Version"));
    } catch (IllegalArgumentException | IOException ex) {
      result.put("version", "Unknown");
    }
    return ResponseEntity.ok(result);
  }
}
