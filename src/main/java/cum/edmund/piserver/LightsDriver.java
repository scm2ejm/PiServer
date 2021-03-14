package cum.edmund.piserver;

import com.pi4j.io.gpio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LightsDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(LightsDriver.class);

    private static final LightsDriver INSTANCE = new LightsDriver();

    private final GpioPinDigitalOutput headlights;

    private LightsDriver() {
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // Headlights are gpio pin #01. Provision as an output pin and turn on
        headlights = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        // set shutdown state
        headlights.setShutdownOptions(true, PinState.LOW);
    }

    public void headlights(boolean on) {
        if (on) {
            headlights.high();
        } else {
            headlights.low();
        }
    }

    public static LightsDriver getInstance() {
        return INSTANCE;
    }
}
