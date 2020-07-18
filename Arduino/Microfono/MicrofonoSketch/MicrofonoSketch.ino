/* Microfono */

int analogPin = A1; 
int sample = 0; 

 
void setup(){
  
  //iniciamos el puerto serie
  Serial.begin(9600);
}
 
void loop(){
  
  
   sample = analogRead(analogPin);  // read the input pin
  
  //Imprimimos el valor medido en nivel
  Serial.print("Valor en nivel: ");
  Serial.println(sample);
  ////Imprimimos el valor medido en mV  
  //Serial.print("Valor en miliVolts: ");
  //Serial.println(sample);

  
  //incrementamos el contador y esperamos un segundo
  delay(10);
}
