#include <Wire.h>
#include <LiquidCrystal_I2C.h>

const int BUTTON = 2;
const int BUZZER = 8;
char dato = 0;
boolean ALARM = false;
unsigned long time1 = 0;
int cont = 0;

LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup() {
  Wire.begin();
  Serial.begin(38400);  
  
  pinMode(BUTTON, INPUT);
  pinMode(BUZZER, OUTPUT);

  lcd.begin(16, 2);
  lcd.clear();
  lcd.backlight();
}

void loop() {
  if(!ALARM){
    if (Serial.available()){
      dato = Serial.read();
      Serial.flush();
      if(dato == '1'){
        //Hacer todo
        lcd.setCursor(0, 0);
        lcd.print("No presione");
        lcd.setCursor(0, 1);
        lcd.print("el boton");
        ALARM = true;
      }
    }
  }else{
    tone(BUZZER, 440);
    if (digitalRead(BUTTON)==HIGH){
      ALARM = false;
      lcd.clear();
    }else{
      if (cont > 10){
        ALARM = false;
        lcd.clear();
        cont = 0;
        Serial.print("No apago");
      }else{
        cont++;
      }
    }
    
    delay(500);
    noTone(BUZZER);
  }
}
