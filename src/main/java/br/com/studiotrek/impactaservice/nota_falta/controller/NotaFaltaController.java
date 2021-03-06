package br.com.studiotrek.impactaservice.nota_falta.controller;

import br.com.studiotrek.impactaservice.nota_falta.model.NotaFalta;
import br.com.studiotrek.impactaservice.nota_falta.regra.NotaFaltaRegra;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NotaFaltaController {

    @RequestMapping(value = "/v2/nota-falta", method = RequestMethod.POST)
    public ResponseEntity<NotaFalta> post(@RequestHeader String token, @RequestBody Map<String, String> json) {
        try {
            NotaFaltaRegra notaFaltaRegra = new NotaFaltaRegra(token, json.get("url"));
            NotaFalta notaFalta = notaFaltaRegra.parseHtml();
            notaFalta.setSemestreAc(notaFaltaRegra.isSemestreAc());
            return new ResponseEntity<>(notaFalta, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>((NotaFalta) null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>((NotaFalta) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Deprecated
    @RequestMapping(value = "/nota-falta", method = RequestMethod.POST)
    public ResponseEntity<NotaFalta> post(@RequestBody Map<String, String> json) {
        try {
            NotaFalta notaFalta = new NotaFaltaRegra(json.get("cookie"), json.get("url")).parseHtml();
            return new ResponseEntity<>(notaFalta, HttpStatus.OK);
        } catch (IllegalAccessException ex) {
            return new ResponseEntity<>((NotaFalta) null, HttpStatus.UNAUTHORIZED);
        } catch (Exception ex) {
            return new ResponseEntity<>((NotaFalta) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
