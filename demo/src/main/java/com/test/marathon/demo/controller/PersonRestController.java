package com.test.marathon.demo.controller;

import com.test.marathon.demo.dao.PersonRepository;
import com.test.marathon.demo.entity.Person;
import com.test.marathon.demo.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonRestController {

  @Autowired
  PersonRepository personRepository;

  @CrossOrigin(origins = "*")
  @PostMapping(value = "/register")
  public @ResponseBody Map<String, Object> registerPerson(HttpServletRequest r) {
    Map<String, Object> map = new HashMap<>();
    try {
      final String baseUrl = new StringBuilder("http://wsruc.com/Ruc2WS_JSON.php?tipo=")
                              .append(r.getParameter("type"))
                              .append("&ruc=")
                              .append(r.getParameter("ruc"))
                              .append("&token=cXdlcnR5bGFtYXJja19zYUBob3RtYWlsLmNvbXF3ZXJ0eQ==")
                              .toString();
      URI uri = null;
      Person person = new Person();
      try {
        uri = new URI(baseUrl);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> result = restTemplate.getForEntity(uri, Person.class);
        if (result.getStatusCodeValue() == 200) {
          if (result.getBody().getRuc() != null) {
            person = personRepository.save(result.getBody());
            map.put("responseBody", person);
            map.put("responseStatus", Constant.SUCCES_RESPONSE_STATUS);
            map.put("responseMessage", "Se registró la persona correctamente.");
          } else {
            map.put("responseStatus", Constant.BAD_RESPONSE_STATUS);
            map.put("responseMessage", "Datos incorrectos.");
          }
        }
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      map.put("responseStatus", Constant.BAD_RESPONSE_STATUS);
      map.put("responseMessage", Constant.BAD_RESPONSE_MESSAGE);
    }
    return map;
  }

  @CrossOrigin(origins = "*")
  @GetMapping("/find/{ruc}")
  public @ResponseBody Map<String, Object> findPerson(@PathVariable(value = "ruc") String ruc) {
    Map<String, Object> map = new HashMap<>();

    try {
      Person person = new Person();
      if (personRepository.findByRuc(ruc).size() > 0){
        person = personRepository.findByRuc(ruc).get(0);
      }
      if (person.getId() != null) {
        map.put("responseBody", person);
        map.put("responseStatus", Constant.SUCCES_RESPONSE_STATUS);
        map.put("responseMessage", "Búsqueda exitosa.");
      } else {
        map.put("responseStatus", Constant.BAD_RESPONSE_STATUS);
        map.put("responseMessage", "Persona no registrada.");
      }

    } catch (Exception ex){
      map.put("responseStatus", Constant.BAD_RESPONSE_STATUS);
      map.put("responseMessage", Constant.BAD_RESPONSE_MESSAGE);
    }
    return map;
  }
}
