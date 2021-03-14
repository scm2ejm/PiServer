package cum.edmund.piserver;

import com.pi4j.io.gpio.*;
import com.pi4j.util.CommandArgumentParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PwmDriver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PwmDriver.class);

    private static final float MAX = 25;
    private static final float MIN = 6;
    private static final float RANGE = MAX - MIN;

    private static final PwmDriver INSTANCE = new PwmDriver();

    private final GpioPinDigitalOutput headlights;
    private final GpioPinPwmOutput pwm;


    private PwmDriver() {
        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // Provision PWM (turning servo) as pin #00
        Pin pin = CommandArgumentParser.getPin(RaspiPin.class, RaspiPin.GPIO_00);
        pwm = gpio.provisionSoftPwmOutputPin(pin);
        pwm.setPwmRange(100);

        // Headlights are gpio pin #01. Provision as an output pin and turn on
        headlights = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "MyLED", PinState.HIGH);

        // set shutdown state
        headlights.setShutdownOptions(true, PinState.LOW);
        pwm.setShutdownOptions(true, PinState.LOW);
    }

    public void pwm(float direction) {
        int newPwm = scale(direction);
        pwm.setPwm(newPwm);
    }

    private int scale(float direction) {
        // Scale direction (-1 to 1) to between 0 and 1
        float zeroToOne = (direction / 2f) + 0.5f;

        // Scale direction in appropriate range
        float scaled = zeroToOne * RANGE;

        // Shift direction between min and max
        float shifted = scaled + MIN;

        int intValue = (int) shifted;

        LOGGER.info("Input direction: {}, PWM value: {}", direction, intValue);

        return intValue;
    }

    public static PwmDriver getInstance() {
        return INSTANCE;
    }
}
