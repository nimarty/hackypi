#include "memfunctions.h"

#include <chrono>
#include <cstring>
#include <ctime>
#include <climits>
#include <cerrno>
#include <iostream>
#include <thread>

#define MS_MIN 5000

using namespace std::chrono_literals;

void logFreeMem(long value) {
   std::time_t result = std::time(nullptr);
   std::cout << result << " " << value << std::endl;
}

bool strToInt(char* str, int& result) {
   char* p;
   errno = 0; // not 'int errno', because the '#include' already defined it
   long arg = strtol(str, &p, 10);
   if (*p != '\0' || errno != 0) {
      return false;
   }

   if (arg < MS_MIN || arg > INT_MAX) {
      return false;
   }
   result = static_cast<int>(arg);
   return true;
}

int main(int argc, char *argv[])
{
   if(argc == 3 && strcmp(argv[1],"c") == 0) {
      int ms = 0;
      if(!strToInt(argv[2], ms)) {
         return 1;
      }
      for(;;) {
         long value = MemFunctions::system_mem_free();
         logFreeMem(value);
         std::this_thread::sleep_for(std::chrono::milliseconds(ms));
      }
   }
   else if(argc == 2) {
      return 1;
   }
   else {
      long value = MemFunctions::system_mem_free();
      logFreeMem(value);
   }
   return 0;
}
