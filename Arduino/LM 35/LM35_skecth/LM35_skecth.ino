int TEMPERATURE_SENSOR;
float temperature;

void setup() {
  Serial.begin(9600);
}

void loop() {
  TEMPERATURE_SENSOR = analogRead(A0);
  temperature = convertToCelsius();
  Serial.println(temperature, 1);
  delay(2000);
}

float convertToCelsius(){
  return ((TEMPERATURE_SENSOR * 5000.0) / 1023) / 10;
}
