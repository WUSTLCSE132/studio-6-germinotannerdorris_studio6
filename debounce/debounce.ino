#define CHECK_INTERVAL 100

const int buttonPin = 2;
boolean buttonState;
unsigned long checkButton;
int pressCounts = 0;

void buttonPressed() {
  if(digitalRead(buttonPin) == LOW){
    checkButton = millis() + CHECK_INTERVAL;
  }
  if(millis() >= checkButton){
      pressCounts++;
      Serial.print("Pressed: ");
      Serial.print(pressCounts);
      Serial.println(" times");
  }
}

void setup() {
  pinMode(buttonPin, INPUT_PULLUP);
  attachInterrupt(digitalPinToInterrupt(buttonPin), buttonPressed, CHANGE);
  Serial.begin(9600);
}

void loop() {
    
}
