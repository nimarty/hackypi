#include "memfunctions.h"

#include <chrono>
#include <cstring>
#include <future>
#include <iostream>
#include <thread>

using namespace std::chrono_literals;

int main(int argc, char *argv[])
{
   if(argc == 2 && strcmp(argv[1],"c") == 0) {
      
      for(;;) {
         std::future<long> result = std::async(std::launch::async, MemFunctions::system_mem_free);
         long value = result.get();
         std::cout << "free: " << value << std::endl;
         std::this_thread::sleep_for(10000ms);
      }
   }
   else {
      long memFree = MemFunctions::system_mem_free();
      std::cout << "free: " << memFree << std::endl;
   }
}
