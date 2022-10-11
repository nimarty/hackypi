#include "memfunctions.h"

#include <chrono>
#include <cstring>
#include <iostream>
#include <thread>

using namespace std::chrono_literals;

void logFreeMem(long value) {
   std::cout << "free: " << value << std::endl;
}

int main(int argc, char *argv[])
{
   if(argc == 2 && strcmp(argv[1],"c") == 0) {
      for(;;) {
         long value = MemFunctions::system_mem_free();
         logFreeMem(value);
         std::this_thread::sleep_for(10000ms);
      }
   }
   else {
      long value = MemFunctions::system_mem_free();
      logFreeMem(value);
   }
   return 0;
}
