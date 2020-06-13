#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup() {
  Wire.begin();
  lcd.begin(16, 2);
  lcd.clear();
  lcd.backlight();
}

void loop() {
  lcd.setCursor(0, 0);
  lcd.print("Dont press");
  lcd.setCursor(0, 1);
  lcd.print("the boton");
  delay(1000);
  lcd.clear();
  delay(1000);
}
