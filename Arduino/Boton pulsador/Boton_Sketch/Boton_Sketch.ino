int led = 13;
int button = 3;
int pressedValue = 0;

void setup() {
  pinMode(led, OUTPUT);
  pinMode(button, INPUT);
  digitalWrite(led, LOW);
}

void loop() {
  pressedValue = digitalRead(button);
  if (pressedValue == HIGH){
    digitalWrite(led, HIGH);
  }
  else{
    digitalWrite(led, LOW);
  }
}
