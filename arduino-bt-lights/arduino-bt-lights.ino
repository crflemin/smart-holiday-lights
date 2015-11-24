#include <Adafruit_NeoPixel.h>

#define NEO_PIN 12
#define LED_COUNT 30

Adafruit_NeoPixel strip = Adafruit_NeoPixel(LED_COUNT, NEO_PIN, NEO_GRB + NEO_KHZ800); 

char incomingByte, colors[3], patternType;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  strip.begin();
  
  colors[0] = 0;
  colors[1] = 100;
  colors[2] = 0;
  
  patternType = 1;
}

void loop() {
  if (Serial.available() > 0)
  {
    incomingByte = (char)Serial.read();

    // packet format will be a single character to indicate type of command,
    // followed by further instructions specific to the command
    switch (incomingByte)
    {
      // send a 'c' prior to programming a color command
      // color command will consist of three successive bytes
      // byte 1 = red, byte 2  = green, byte 3 = blue
       case 'c':
       {
         int count = 0;
         while (count < 3) 
         {
           if (Serial.available() > 0)
           {
             colors[count] = (char)Serial.read();
             count++;
           }
         }
       }
       break;
     
       // send a 'p' prior to programming a pattern command
       // pattern command will consist of one byte indicating pattern index
       case 'p':
       {
         int count = 0;
         while (count < 1) 
         {
           if (Serial.available() > 0)
           {
             patternType = (char)Serial.read();
             count++;
           }
         }
       }
       break;
       
       default:
         break;
    }
  }
  
  // decide which light pattern to program on the LEDs
  switch (patternType)
  {
      case 0:
        showPatternA();
        break;
        
      case 1':
        showPatternB();
        break;
         
      case 2:
        showPatternC();
        break;
        
      default:
        break; 
  }
}

void showPatternA()
{
  for (unsigned int i = 0; i < LED_COUNT; i++)
  {
    strip.setPixelColor(i, colors[0], colors[1], colors[2]);
  } 
  strip.show();
  delay(500);
}

void showPatternB() 
{
  for (unsigned int i = 0; i < LED_COUNT; i+=2)
  {
    strip.setPixelColor(i, colors[0], colors[1], colors[2]);
  } 
  strip.show();
  delay(500);
  strip.clear();
  for (unsigned int i = 0; i < LED_COUNT; i+=2)
  {
    strip.setPixelColor(i+1, colors[0], colors[1], colors[2]);
  } 
  strip.show();
  delay(500);
  strip.clear();
}

void showPatternC() 
{
  for (unsigned int i = 0; i < LED_COUNT; i+=2)
  {
    strip.setPixelColor(i, colors[0], colors[1], colors[2]);
    strip.show();
    delay(100);
    strip.clear();
  } 
}
