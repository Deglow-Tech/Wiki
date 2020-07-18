/*
  medicion basica
*/

#include <Wire.h>
#include "MAX30105.h"

MAX30105 sensor;

#define debug Serial //Uncomment this line if you're using an Uno or ESP
//#define debug SerialUSB //Uncomment this line if you're using a SAMD21

void setup()
{
  debug.begin(9600);
  debug.println("MAX30105 Basic Readings Example");

  // Initialize sensor
  if (sensor.begin() == false) // comienza la transmicion I2C
  {
    debug.println("MAX30105 was not found. Please check wiring/power. ");
    while (1);
  }


  //Setup
  byte ledBrightness = 0x1F; //Options: 0=Off to 255=50mA
  byte sampleAverage = 4; //Options: 1, 2, 4, 8, 16, 32
  byte ledMode = 2; //Options: 1 = Red only (solo HR), 2 = Red + IR (HR + Sp02), 3 = no conviene para el 30102
  int sampleRate = 100; //Options: 50, 100, 200, 400, 800, 1000, 1600, 3200
  int pulseWidth = 411; //Options: 69, 118, 215, 411
  int adcRange = 4096; //Options: 2048, 4096, 8192, 16384

  sensor.setup(ledBrightness, sampleAverage, ledMode, sampleRate, pulseWidth, adcRange); //Configure sensor with these settings

}

void loop()
{

  long red;
  long IR;
  //debug.print("t");
  red = sensor.getRed() - 20000;
  if(red<110000)
  {
    red = 110000;
  }

  IR = sensor.getIR();
  if(IR<110000)
  {
    IR = 110000;
  }

  debug.print(red);
  debug.print(", ");
  debug.print(IR);
  debug.print(", ");
  //debug.print(sensor.getRed());
  //debug.print(", ");
  //debug.print(sensor.getIR());
  //debug.print(", ");

  debug.println();
}
