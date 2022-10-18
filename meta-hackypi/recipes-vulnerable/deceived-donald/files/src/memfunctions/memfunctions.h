#pragma once

namespace MemFunctions {

    void process_mem_usage(double& vm_usage, double& resident_set);    

    long system_mem_free();
}