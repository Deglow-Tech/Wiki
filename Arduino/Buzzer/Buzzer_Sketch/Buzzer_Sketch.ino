const int BUZZER = 8;

void setup(){
  pinMode(BUZZER, OUTPUT);
}

void loop(){
  tone(BUZZER, 440); //Make a tone of 440hz
  delay(1000);
  noTone(BUZZER);
  delay(500);
}