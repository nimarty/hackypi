#include <cstring>
#include <iostream>
#include <unistd.h>

using std::cin;
using std::cout;

unsigned produced = 335;
unsigned consumed = 274;

void unused();

int menu() {
  char choice[2];
  if (consumed) {
    cout << "1) Disconnect consumers\n";
  } else {
    cout << "1) Connect consumers\n";
  }
  cout << "2) Exit\n";
  cout << "> ";
  scanf("%s", choice);
  return choice[0];
}

void update_power_budget() {
  int pdiff = (float)rand() * 11 / RAND_MAX - 5;
  if (produced < 300)
    pdiff = abs(pdiff);
  if (produced > 400)
    pdiff = -abs(pdiff);
  produced += pdiff;

  int cdiff = (float)rand() * 11 / RAND_MAX - 5;
  if (produced < 250)
    cdiff = abs(cdiff);
  if (produced > 350)
    cdiff = -abs(cdiff);
  consumed += cdiff;
}

void logged_in() {
  while (true) {
    update_power_budget();
    cout << "Current produced: " << produced << "W\n";
    cout << "Current consumed: " << consumed << "W\n";
    cout << "\n";
    switch (menu()) {
    case '1': {
      consumed = consumed ? 0 : 274;
      continue;
    }
    case '2': {
      return;
    }
    case '3': {
      unused();
      continue;
    }
    default:
      cout << "Invalid selection\n\n";
    }
  }
}

int main() {
  char expected_pin[5];
  FILE *f = fopen("/etc/hhcli", "r");
  fread(expected_pin, 4, 1, f);
  expected_pin[4] = 0;
  fclose(f);

  setuid(0);

  cout << "HEMS (Home Energy Management System) Service interface\n";
  cout << "======================================================\n";
  cout << '\n';
  cout << "To prohibit unauthorized access\n";
  cout << "please identify yourself with\n";
  cout << "your PIN.\n";
  cout << '\n';

  printf("%08x\n\n", unused);

  while (true) {
    char pin[5];
    cout << "Enter PIN: ";
    cin >> pin;
    if (strcmp(pin, expected_pin)) {
      cout << "Invalid PIN\n";
    } else {
      cout << "PIN accepted\n";
      logged_in();
    }
  }
}

void unused() {
  char* const x[] = {"cat", "/etc/flag", 0};
  execv("/bin/busybox", x);
}
