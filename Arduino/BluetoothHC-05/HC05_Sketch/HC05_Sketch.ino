//#include <SoftwareSerial.h>

//SoftwareSerial miBT(10, 11);
char DATO = 0;
int BUZZER = 5;

void setup() {
  //miBT.begin(38400);
  Serial.begin(38400);  
  Serial.println("MODULO CONECTADO");
  Serial.print("#");
  pinMode(BUZZER, OUTPUT);
}

void loop() {
  if (Serial.available()){
    DATO = Serial.read();
    if (DATO == '1'){
      tone(BUZZER, 440);
    }

    if(DATO == '0'){
      noTone(BUZZER);
    }

    if(DATO == '2'){
      Serial.println("Hola, tu bebe esta de 10");
      Serial.print("#");
    }
  }
}
