package cum.edmund.motor;

import java.util.Scanner;

import com.pi4j.io.gpio.*;
import com.pi4j.wiringpi.Gpio;
import com.pi4j.wiringpi.SoftPwm;

public class Motor {
    static int flag=0;
    static GpioController gpio=null;
    static GpioPinDigitalOutput GPIO_02=null;
    static GpioPinDigitalOutput GPIO_03=null;
    static GpioPinDigitalOutput GPIO_04=null;
    static GpioPinDigitalOutput GPIO_00=null;

    static GpioPinPwmOutput GPIO_01=null;//ENA
    static GpioPinPwmOutput GPIO_23=null;//ENB
    public static void now(int a) {
        if(Motor.flag==0){
            gpio = GpioFactory.getInstance();
            Gpio.wiringPiSetup();
            SoftPwm.softPwmCreate(1, 50,100);//Pin position, pin setting initial value, pin maximum value
            SoftPwm.softPwmCreate(23,50,100);//Pin position, pin setting initial value, pin maximum value
            GPIO_00 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "IN_1", PinState.LOW);
            GPIO_02 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "IN_2", PinState.LOW);
            GPIO_03 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "IN_3", PinState.LOW);
            GPIO_04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "IN_4", PinState.LOW);
            flag=1;
        }
        switch(a) {
            case 1:GPIO_03.setState(PinState.HIGH);GPIO_04.setState(PinState.LOW);break;
            case 2:GPIO_03.setState(PinState.LOW);GPIO_04.setState(PinState.HIGH);break;
            case 3:GPIO_00.setState(PinState.HIGH);GPIO_02.setState(PinState.LOW);break;
            case 4:GPIO_00.setState(PinState.LOW);GPIO_02.setState(PinState.HIGH);break;
            case 5:SoftPwm.softPwmWrite(1, 30);SoftPwm.softPwmWrite(23, 30);break;//Set enable A B to 30%
            case 6:SoftPwm.softPwmWrite(1, 60);SoftPwm.softPwmWrite(23, 60);break;//Set enable A B to 60%
            case 7:SoftPwm.softPwmWrite(1, 90);SoftPwm.softPwmWrite(23, 90);break;//Set enable A B to 90%
            case 8:GPIO_00.setState(PinState.LOW);GPIO_02.setState(PinState.LOW);GPIO_03.setState(PinState.LOW);GPIO_04.setState(PinState.LOW);break;//Close all interfaces
        }
    }
    public static void main(String[] args) {
        System.out.println("begin");
        Scanner input=new Scanner(System.in);
        while(true) {
            int a=input.nextInt();
            Motor.now(a);
        }
    }
}
