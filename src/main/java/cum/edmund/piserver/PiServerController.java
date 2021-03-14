package cum.edmund.piserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PiServerController {

    private final Logger logger = LoggerFactory.getLogger(PiServerController.class);

    @PostMapping("/drive")
    public ResponseEntity<Direction> login(@RequestBody Direction direction) {

        if (direction != null) {
            PwmDriver.getInstance().pwm(direction.getLeftRight());
        }

        return new ResponseEntity(direction, HttpStatus.OK);

    }
}
