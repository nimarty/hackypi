#include "memfunctions.h"

#include <iostream>

int main()
{
   using std::cout;
   using std::endl;

   double vm, rss;
   MemFunctions::process_mem_usage(vm, rss);
   cout << "VM: " << vm << "; RSS: " << rss << endl;
}